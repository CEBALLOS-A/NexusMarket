package com.nexusmarket.domain.ports.in;

import com.nexusmarket.domain.model.EmployeeType;
import com.nexusmarket.domain.model.User;
import com.nexusmarket.domain.model.UserStatus;

import java.util.List;

public interface UserManagementPort {

    boolean registerUser(User user);

    void updateUser(int userId, String fullName, String email);

    User findUser(int userId);

    void changeStatus(int userId, UserStatus newStatus);

    void assignRole(int userId, EmployeeType newRole);

    List<User> listUsers();

    boolean validateData(User user);
}
