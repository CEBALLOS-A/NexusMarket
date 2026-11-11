package com.nexusmarket.model;

import com.nexusmarket.enums.EmployeeType;
import com.nexusmarket.enums.UserStatus;

public class Administrator extends User {

    public Administrator() {
    }

    public Administrator(int id, String fullName, String email, EmployeeType role, UserStatus status) {
        super(id, fullName, email, role, status);
    }
}
