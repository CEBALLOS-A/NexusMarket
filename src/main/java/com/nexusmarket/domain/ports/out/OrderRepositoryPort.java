package com.nexusmarket.domain.ports.out;

import com.nexusmarket.domain.model.Order;

import java.util.List;

public interface OrderRepositoryPort {

    Order findById(int id);

    List<Order> findAll();

    void save(Order order);
}
