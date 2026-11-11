package com.nexusmarket.domain.ports.in;

public interface SupervisorPort {

    void viewUsers();

    void viewOrder(int orderId);

    void viewInventory(String product, String warehouse);

    void viewShipment(int shipmentId);

    void viewReports();

    void checkOperationalStatus();
}
