package com.nexusmarket.domain.ports.out;

import com.nexusmarket.domain.model.User;

import java.util.List;

public interface UserRepositoryPort {

    User findById(int id);

    User findByEmail(String email);

    List<User> findAll();

    void save(User user);

    void update(User user);
}
