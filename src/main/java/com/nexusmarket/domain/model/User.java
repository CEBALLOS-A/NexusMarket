package com.nexusmarket.domain.model;

import com.nexusmarket.domain.model.EmployeeType;
import com.nexusmarket.domain.model.UserStatus;

public class User {

    private int id;
    private String fullName;
    private String email;
    private EmployeeType role;
    private UserStatus status;

    public User() {
    }

    public User(int id, String fullName, String email, EmployeeType role, UserStatus status) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmployeeType getRole() {
        return role;
    }

    public void setRole(EmployeeType role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
