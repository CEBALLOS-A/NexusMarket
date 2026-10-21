package com.nexusmarket.service;

import com.nexusmarket.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final List<User> users = new ArrayList<>();

    public void registerUser(User user) {
        if (validateData(user)) {
            users.add(user);
            System.out.println("User successfully registered.");
        } else {
            System.out.println("The user could not be registered, data is missing.");
        }
    }

    public void updateUser(int userId, String fullName, String email) {
        User user = findUser(userId);
        if (user != null) {
            user.setFullName(fullName);
            user.setEmail(email);
            System.out.println("User successfully updated.");
        } else {
            System.out.println("No user found with that id.");
        }
    }

    public User findUser(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    public void changeStatus(int userId, String newStatus) {
        User user = findUser(userId);
        if (user != null) {
            user.setStatus(newStatus);
            System.out.println("User status successfully updated.");
        } else {
            System.out.println("No user found with that id.");
        }
    }

    public void assignRole(int userId, String newRole) {
        User user = findUser(userId);
        if (user != null) {
            user.setRole(newRole);
            System.out.println("Role successfully assigned.");
        } else {
            System.out.println("No user found with that id.");
        }
    }

    public List<User> listUsers() {
        return users;
    }

    public boolean validateData(User user) {
        if (user.getFullName() == null || user.getFullName().isEmpty()) {
            return false;
        }
        return user.getEmail() != null && !user.getEmail().isEmpty();
    }
}
