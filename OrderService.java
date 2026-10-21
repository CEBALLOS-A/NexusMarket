package com.nexusmarket.service;

import com.nexusmarket.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final List<Order> orders = new ArrayList<>();

    public void createOrder(Order order) {
        order.setStatus("Created");
        orders.add(order);
        System.out.println("Order successfully created.");
    }

    public Order findOrder(int orderId) {
        for (Order order : orders) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public void confirmOrder(int orderId) {
        updateStatus(orderId, "Pending Payment", "Order confirmed, pending payment.");
    }

    public void validatePayment(int orderId) {
        updateStatus(orderId, "Paid", "Payment successfully validated.");
    }

    public void dispatchOrder(int orderId) {
        updateStatus(orderId, "Dispatched", "Order successfully dispatched.");
    }

    public void confirmDelivery(int orderId) {
        updateStatus(orderId, "Delivered", "Delivery successfully confirmed.");
    }

    public void completeOrder(int orderId) {
        Order order = findOrder(orderId);
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }
        order.setStatus("Completed");
        System.out.println("Order successfully completed.");
    }

    private void updateStatus(int orderId, String newStatus, String successMessage) {
        Order order = findOrder(orderId);
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }
        if ("Completed".equals(order.getStatus())) {
            System.out.println("The order is already completed and cannot be modified.");
            return;
        }
        order.setStatus(newStatus);
        System.out.println(successMessage);
    }
}
