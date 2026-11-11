package com.nexusmarket.model;

import com.nexusmarket.enums.EmployeeType;
import com.nexusmarket.enums.UserStatus;

public class LogisticsOperator extends User {

    public LogisticsOperator() {
    }

    public LogisticsOperator(int id, String fullName, String email, EmployeeType role, UserStatus status) {
        super(id, fullName, email, role, status);
    }
}
