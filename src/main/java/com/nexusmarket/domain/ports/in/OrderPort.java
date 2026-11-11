package com.nexusmarket.domain.ports.in;

import com.nexusmarket.domain.model.Order;

public interface OrderPort {

    void createOrder(Order order);

    Order findOrder(int orderId);

    void confirmOrder(int orderId);

    void validatePayment(int orderId);

    void dispatchOrder(int orderId);

    void confirmDelivery(int orderId);

    void completeOrder(int orderId);
}
