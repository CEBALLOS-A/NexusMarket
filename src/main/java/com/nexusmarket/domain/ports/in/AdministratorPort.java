package com.nexusmarket.domain.ports.in;

import com.nexusmarket.domain.model.Seller;
import com.nexusmarket.domain.model.Warehouse;

public interface AdministratorPort {

    void registerSeller(Seller seller);

    void updateSeller(int userId, String fullName, String email);

    void addWarehouse(Warehouse warehouse);

    void updateWarehouse(int warehouseId, String name);

    void viewUsers();

    void viewOrder(int orderId);

    void viewInventory(String product, String warehouse);

    void viewShipment(int shipmentId);

    void viewReports();

    void checkOperationalStatus();
}
