package com.nexusmarket.service;

import com.nexusmarket.model.Inventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryService {

    private final List<Inventory> inventories = new ArrayList<>();

    public void registerStock(Inventory inventory) {
        inventories.add(inventory);
        System.out.println("Stock successfully registered.");
    }

    public Inventory findStock(String product, String warehouse) {
        for (Inventory inventory : inventories) {
            if (inventory.getProduct().equals(product) && inventory.getWarehouse().equals(warehouse)) {
                return inventory;
            }
        }
        return null;
    }

    public void queryByWarehouse(String warehouse) {
        for (Inventory inventory : inventories) {
            if (inventory.getWarehouse().equals(warehouse)) {
                System.out.println(inventory.getProduct() + " - Quantity: " + inventory.getAvailableQuantity()
                        + " - Status: " + inventory.getStockStatus());
            }
        }
    }

    public boolean validateAvailability(String product, String warehouse, int quantity) {
        Inventory inventory = findStock(product, warehouse);
        if (inventory == null) {
            return false;
        }
        if ("Damaged".equals(inventory.getStockStatus())) {
            return false;
        }
        return inventory.getAvailableQuantity() >= quantity;
    }

    public void reserveInventory(String product, String warehouse, int quantity) {
        if (validateAvailability(product, warehouse, quantity)) {
            Inventory inventory = findStock(product, warehouse);
            inventory.setAvailableQuantity(inventory.getAvailableQuantity() - quantity);
            System.out.println("Inventory successfully reserved.");
        } else {
            System.out.println("Not enough availability to reserve.");
        }
    }

    public void releaseInventory(String product, String warehouse, int quantity) {
        Inventory inventory = findStock(product, warehouse);
        if (inventory != null) {
            inventory.setAvailableQuantity(inventory.getAvailableQuantity() + quantity);
            System.out.println("Inventory successfully released.");
        } else {
            System.out.println("Inventory not found.");
        }
    }

    public void registerOutput(String product, String warehouse, int quantity) {
        Inventory inventory = findStock(product, warehouse);
        if (inventory == null) {
            System.out.println("Inventory not found.");
            return;
        }
        if (inventory.getAvailableQuantity() < quantity) {
            System.out.println("Not enough quantity to register the output.");
            return;
        }
        inventory.setAvailableQuantity(inventory.getAvailableQuantity() - quantity);
        System.out.println("Output successfully registered.");
    }

    public void adjustInventory(String product, String warehouse, int newQuantity) {
        Inventory inventory = findStock(product, warehouse);
        if (inventory == null) {
            System.out.println("Inventory not found.");
            return;
        }
        if (newQuantity < 0) {
            System.out.println("Negative stock is not allowed.");
            return;
        }
        inventory.setAvailableQuantity(newQuantity);
        System.out.println("Inventory successfully adjusted.");
    }

    public void registerReturn(String product, String warehouse, int quantity) {
        Inventory inventory = findStock(product, warehouse);
        if (inventory != null) {
            inventory.setAvailableQuantity(inventory.getAvailableQuantity() + quantity);
            System.out.println("Return successfully registered in the inventory.");
        } else {
            System.out.println("Inventory not found.");
        }
    }
}
