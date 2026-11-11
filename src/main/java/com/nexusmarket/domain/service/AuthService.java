package com.nexusmarket.domain.service;

import com.nexusmarket.domain.ports.in.PublicAccessPort;

import com.nexusmarket.domain.model.EmployeeType;
import com.nexusmarket.domain.model.UserStatus;
import com.nexusmarket.domain.model.Administrator;
import com.nexusmarket.domain.model.Buyer;
import com.nexusmarket.domain.model.LogisticsOperator;
import com.nexusmarket.domain.model.Seller;
import com.nexusmarket.domain.model.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class AuthService implements PublicAccessPort {

    public static final int MAX_FAILED_ATTEMPTS = 3;
    public static final String PASSWORD_RULES =
            "8 to 64 characters, with an uppercase letter, a lowercase letter, a number "
                    + "and a special character, and no spaces.";

    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 64;
    private static final int SALT_BYTES = 16;
    private static final int HASH_BITS = 256;
    private static final int ITERATIONS = 65_536;
    private static final String INVALID_LOGIN = "Invalid ID or password.";
    private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)+$");

    private final Map<Integer, Credential> credentials = new HashMap<>();
    private final SecureRandom random = new SecureRandom();
    private final UserService userService;
    private final BuyerService buyerService;
    private final SellerService sellerService;

    public AuthService(UserService userService, BuyerService buyerService, SellerService sellerService) {
        this.userService = userService;
        this.buyerService = buyerService;
        this.sellerService = sellerService;
    }

    /** Public registration creates the account locally; the UI then persists it to both databases. */
    public User selfRegister(int id, String fullName, String email, EmployeeType role,
                             String mainAddress, String password, String confirmPassword) {
        if (role != EmployeeType.BUYER && role != EmployeeType.SELLER) {
            throw new IllegalArgumentException("Only buyer and seller accounts can be created from the registration form.");
        }
        return register(id, fullName, email, role, mainAddress, password, confirmPassword);
    }

    /** Creates an account of any type (used by administrators). */
    public User register(int id, String fullName, String email, EmployeeType role,
                         String mainAddress, String password, String confirmPassword) {
        validateRegistration(id, fullName, email, role, password, confirmPassword);

        User user = createUser(id, fullName.trim(), email.trim(), role, mainAddress);
        if (!userService.registerUser(user)) {
            throw new IllegalArgumentException("The user could not be registered.");
        }
        if (user instanceof Buyer) {
            buyerService.registerBuyer((Buyer) user);
        } else if (user instanceof Seller) {
            sellerService.registerSeller((Seller) user);
        }
        credentials.put(id, newCredential(password));
        return user;
    }

    private void validateRegistration(int id, String fullName, String email, EmployeeType role,
                                      String password, String confirmPassword) {
        if (id <= 0) {
            throw new IllegalArgumentException("The ID must be a positive number.");
        }
        if (userService.findUser(id) != null) {
            throw new IllegalArgumentException("A user with that ID already exists.");
        }
        validateName(fullName);
        validateEmail(email, id);
        if (role == null) {
            throw new IllegalArgumentException("Select an account type.");
        }
        if (password == null || !password.equals(confirmPassword)) {
            throw new IllegalArgumentException("The passwords do not match.");
        }
        validatePassword(password);
    }

    public User login(int id, String password) {
        User user = userService.findUser(id);
        Credential credential = credentials.get(id);
        if (user == null || credential == null || password == null) {
            throw new IllegalArgumentException(INVALID_LOGIN);
        }
        // An administrator reactivated the account: start counting again.
        if (user.getStatus() == UserStatus.ACTIVE && credential.failedAttempts >= MAX_FAILED_ATTEMPTS) {
            credential.failedAttempts = 0;
        }
        if (!matches(password, credential)) {
            credential.failedAttempts++;
            if (credential.failedAttempts >= MAX_FAILED_ATTEMPTS && user.getStatus() == UserStatus.ACTIVE) {
                user.setStatus(UserStatus.SUSPENDED);
                throw new IllegalArgumentException(
                        "Too many failed attempts. The account was suspended, contact an administrator.");
            }
            throw new IllegalArgumentException(INVALID_LOGIN);
        }
        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new IllegalArgumentException("This account is not active, contact an administrator.");
        }
        credential.failedAttempts = 0;
        return user;
    }

    public void changePassword(int userId, String currentPassword, String newPassword, String confirmPassword) {
        Credential credential = credentials.get(userId);
        if (credential == null || currentPassword == null || !matches(currentPassword, credential)) {
            throw new IllegalArgumentException("The current password is incorrect.");
        }
        if (newPassword == null || !newPassword.equals(confirmPassword)) {
            throw new IllegalArgumentException("The new passwords do not match.");
        }
        validatePassword(newPassword);
        if (newPassword.equals(currentPassword)) {
            throw new IllegalArgumentException("The new password must be different from the current one.");
        }
        credentials.put(userId, newCredential(newPassword));
    }

    public void updateProfile(int userId, String fullName, String email) {
        if (userService.findUser(userId) == null) {
            throw new IllegalArgumentException("No user found with that ID.");
        }
        validateName(fullName);
        validateEmail(email, userId);
        userService.updateUser(userId, fullName.trim(), email.trim());
    }

    public static int parseId(String text) {
        String value = text == null ? "" : text.trim();
        if (!value.matches("\\d{1,9}") || Integer.parseInt(value) <= 0) {
            throw new IllegalArgumentException("The ID must be a positive number of up to 9 digits.");
        }
        return Integer.parseInt(value);
    }

    public static void validatePassword(String password) {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("The password must have between 8 and 64 characters.");
        }
        if (password.chars().anyMatch(Character::isWhitespace)) {
            throw new IllegalArgumentException("The password cannot contain spaces.");
        }
        if (password.chars().noneMatch(Character::isUpperCase)) {
            throw new IllegalArgumentException("The password must include an uppercase letter.");
        }
        if (password.chars().noneMatch(Character::isLowerCase)) {
            throw new IllegalArgumentException("The password must include a lowercase letter.");
        }
        if (password.chars().noneMatch(Character::isDigit)) {
            throw new IllegalArgumentException("The password must include a number.");
        }
        if (password.chars().allMatch(Character::isLetterOrDigit)) {
            throw new IllegalArgumentException("The password must include a special character.");
        }
    }

    private void validateName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("The full name is required.");
        }
    }

    private void validateEmail(String email, int ownerId) {
        if (email == null || !EMAIL.matcher(email.trim()).matches()) {
            throw new IllegalArgumentException("Enter a valid email address.");
        }
        for (User other : userService.listUsers()) {
            if (other.getId() != ownerId && email.trim().equalsIgnoreCase(other.getEmail())) {
                throw new IllegalArgumentException("That email is already registered.");
            }
        }
    }

    private static User createUser(int id, String fullName, String email, EmployeeType role, String mainAddress) {
        UserStatus status = UserStatus.ACTIVE;
        switch (role) {
            case ADMINISTRATOR:
            case SUPERVISOR:
                return new Administrator(id, fullName, email, role, status);
            case SELLER:
                return new Seller(id, fullName, email, role, status);
            case LOGISTICS_OPERATOR:
                return new LogisticsOperator(id, fullName, email, role, status);
            default:
                String address = mainAddress == null ? "" : mainAddress.trim();
                return new Buyer(id, fullName, email, role, status, address, "Regular");
        }
    }

    private Credential newCredential(String password) {
        byte[] salt = new byte[SALT_BYTES];
        random.nextBytes(salt);
        return new Credential(salt, hash(password, salt));
    }

    private static boolean matches(String password, Credential credential) {
        return MessageDigest.isEqual(hash(password, credential.salt), credential.hash);
    }

    private static byte[] hash(String password, byte[] salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, HASH_BITS);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Password hashing is not available.", e);
        }
    }

    private static final class Credential {
        private final byte[] salt;
        private final byte[] hash;
        private int failedAttempts;

        private Credential(byte[] salt, byte[] hash) {
            this.salt = salt;
            this.hash = hash;
        }
    }
}
