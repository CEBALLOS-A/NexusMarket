package com.nexusmarket.domain.service;

import com.nexusmarket.domain.ports.in.UserManagementPort;

import com.nexusmarket.domain.model.EmployeeType;
import com.nexusmarket.domain.model.UserStatus;
import com.nexusmarket.domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService implements UserManagementPort {

    private final List<User> users = new ArrayList<>();

    public boolean registerUser(User user) {
        if (findUser(user.getId()) != null) {
            System.out.println("A user with that id already exists.");
            return false;
        }
        if (validateData(user)) {
            users.add(user);
            System.out.println("User successfully registered.");
            return true;
        }
        System.out.println("The user could not be registered, data is missing.");
        return false;
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

    public void changeStatus(int userId, UserStatus newStatus) {
        User user = findUser(userId);
        if (user != null) {
            user.setStatus(newStatus);
            System.out.println("User status successfully updated.");
        } else {
            System.out.println("No user found with that id.");
        }
    }

    public void assignRole(int userId, EmployeeType newRole) {
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
