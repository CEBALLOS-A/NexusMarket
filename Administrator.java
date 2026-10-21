package com.nexusmarket.model;

public class Administrator extends User {

    public Administrator() {
    }

    public Administrator(int id, String fullName, String email, String role, String status) {
        super(id, fullName, email, role, status);
    }
}
