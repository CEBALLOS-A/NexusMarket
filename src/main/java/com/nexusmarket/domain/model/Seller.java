package com.nexusmarket.domain.model;

import com.nexusmarket.domain.model.EmployeeType;
import com.nexusmarket.domain.model.UserStatus;

public class Seller extends User {

    public Seller() {
    }

    public Seller(int id, String fullName, String email, EmployeeType role, UserStatus status) {
        super(id, fullName, email, role, status);
    }
}
