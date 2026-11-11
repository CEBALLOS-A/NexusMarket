package com.nexusmarket.domain.model;

import com.nexusmarket.domain.model.EmployeeType;
import com.nexusmarket.domain.model.UserStatus;

public class LogisticsOperator extends User {

    public LogisticsOperator() {
    }

    public LogisticsOperator(int id, String fullName, String email, EmployeeType role, UserStatus status) {
        super(id, fullName, email, role, status);
    }
}
