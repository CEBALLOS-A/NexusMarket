package com.nexusmarket.domain.ports.out;

import com.nexusmarket.domain.model.User;

import java.util.List;

public interface AdministrativeReportQueryPort {

    List<User> listUsers();

    long countUsers();
}
