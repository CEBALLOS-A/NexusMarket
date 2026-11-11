package com.nexusmarket.service;

import com.nexusmarket.model.Inventory;
import com.nexusmarket.model.InventoryMovement;

import java.util.ArrayList;
import java.util.List;

public class InventoryMovementService {

    private final List<InventoryMovement> movements = new ArrayList<>();
    private final InventoryService inventoryService;

    public InventoryMovementService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void registerMovement(InventoryMovement movement) {
        if (validateMovement(movement)) {
            movements.add(movement);
            System.out.println("Movement successfully registered.");
        } else {
            System.out.println("Invalid movement type.");
        }
    }

    public boolean validateMovement(InventoryMovement movement) {
        String type = movement.getMovementType();
        return "Incoming".equals(type) || "Reservation".equals(type) || "Outgoing".equals(type)
                || "Adjustment".equals(type) || "Return".equals(type);
    }

    public void applyMovement(InventoryMovement movement) {
        String warehouse = String.valueOf(movement.getWarehouseId());
        String product = movement.getProduct();
        int quantity = movement.getQuantity();

        switch (movement.getMovementType()) {
            case "Incoming":
                inventoryService.registerStock(new Inventory(product, warehouse, quantity, "Available"));
                break;
            case "Reservation":
                inventoryService.reserveInventory(product, warehouse, quantity);
                break;
            case "Outgoing":
                inventoryService.registerOutput(product, warehouse, quantity);
                break;
            case "Adjustment":
                inventoryService.adjustInventory(product, warehouse, quantity);
                break;
            case "Return":
                inventoryService.registerReturn(product, warehouse, quantity);
                break;
            default:
                System.out.println("Invalid movement type.");
        }
    }
}
