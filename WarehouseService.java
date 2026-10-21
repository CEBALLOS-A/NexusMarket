package com.nexusmarket.service;

import com.nexusmarket.model.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class WarehouseService {

    private final List<Warehouse> warehouses = new ArrayList<>();
    private final InventoryService inventoryService;

    public WarehouseService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
        System.out.println("Warehouse successfully registered.");
    }

    public void updateWarehouse(int warehouseId, String name) {
        Warehouse warehouse = findWarehouse(warehouseId);
        if (warehouse != null) {
            warehouse.setName(name);
            System.out.println("Warehouse successfully updated.");
        } else {
            System.out.println("Warehouse not found.");
        }
    }

    public Warehouse findWarehouse(int warehouseId) {
        for (Warehouse warehouse : warehouses) {
            if (warehouse.getId() == warehouseId) {
                return warehouse;
            }
        }
        return null;
    }

    public void queryInventory(int warehouseId) {
        if (findWarehouse(warehouseId) == null) {
            System.out.println("Warehouse not found.");
            return;
        }
        inventoryService.queryByWarehouse(String.valueOf(warehouseId));
    }

    public void associateProduct(int warehouseId, int productId) {
        Warehouse warehouse = findWarehouse(warehouseId);
        if (warehouse != null) {
            warehouse.getAssociatedProducts().add(productId);
            System.out.println("Product successfully associated with the warehouse.");
        } else {
            System.out.println("Warehouse not found.");
        }
    }
}
