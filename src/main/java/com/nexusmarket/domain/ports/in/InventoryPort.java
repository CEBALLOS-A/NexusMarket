package com.nexusmarket.domain.ports.in;

import com.nexusmarket.domain.model.Inventory;

public interface InventoryPort {

    void registerStock(Inventory inventory);

    Inventory findStock(String product, String warehouse);

    void queryByWarehouse(String warehouse);

    boolean validateAvailability(String product, String warehouse, int quantity);

    void reserveInventory(String product, String warehouse, int quantity);

    void releaseInventory(String product, String warehouse, int quantity);

    void registerOutput(String product, String warehouse, int quantity);

    void adjustInventory(String product, String warehouse, int newQuantity);

    void registerReturn(String product, String warehouse, int quantity);
}
