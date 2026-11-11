package com.nexusmarket.domain.ports.in;

import com.nexusmarket.domain.model.EmployeeType;
import com.nexusmarket.domain.model.User;

public interface PublicAccessPort {

    User login(int id, String password);

    User selfRegister(int id, String fullName, String email, EmployeeType role,
                      String mainAddress, String password, String confirmPassword);
}
