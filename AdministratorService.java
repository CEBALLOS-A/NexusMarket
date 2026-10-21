package com.nexusmarket.service;

import com.nexusmarket.model.Inventory;
import com.nexusmarket.model.Order;
import com.nexusmarket.model.Seller;
import com.nexusmarket.model.Shipment;
import com.nexusmarket.model.User;
import com.nexusmarket.model.Warehouse;

/**
 * Service shared by the Administrator and Supervisor roles. Both roles have
 * the same attributes and behaviour, so they are distinguished only by the
 * "role" attribute of {@link com.nexusmarket.model.Administrator}.
 */
public class AdministratorService {

    private final SellerService sellerService;
    private final WarehouseService warehouseService;
    private final UserService userService;
    private final OrderService orderService;
    private final InventoryService inventoryService;
    private final ShipmentService shipmentService;

    public AdministratorService(SellerService sellerService, WarehouseService warehouseService,
                                UserService userService, OrderService orderService,
                                InventoryService inventoryService, ShipmentService shipmentService) {
        this.sellerService = sellerService;
        this.warehouseService = warehouseService;
        this.userService = userService;
        this.orderService = orderService;
        this.inventoryService = inventoryService;
        this.shipmentService = shipmentService;
    }

    public void registerSeller(Seller seller) {
        sellerService.registerSeller(seller);
    }

    public void updateSeller(int userId, String fullName, String email) {
        sellerService.updateSeller(userId, fullName, email);
    }

    public void addWarehouse(Warehouse warehouse) {
        warehouseService.addWarehouse(warehouse);
    }

    public void updateWarehouse(int warehouseId, String name) {
        warehouseService.updateWarehouse(warehouseId, name);
    }

    public void viewUsers() {
        for (User user : userService.listUsers()) {
            System.out.println(user.getId() + " - " + user.getFullName() + " - " + user.getRole());
        }
    }

    public void viewOrder(int orderId) {
        Order order = orderService.findOrder(orderId);
        if (order != null) {
            System.out.println("Order " + order.getId() + " - Status: " + order.getStatus());
        } else {
            System.out.println("Order not found.");
        }
    }

    public void viewInventory(String product, String warehouse) {
        Inventory inventory = inventoryService.findStock(product, warehouse);
        if (inventory != null) {
            System.out.println("Product: " + inventory.getProduct() + " - Quantity: " + inventory.getAvailableQuantity());
        } else {
            System.out.println("Inventory not found.");
        }
    }

    public void viewShipment(int shipmentId) {
        Shipment shipment = shipmentService.findShipment(shipmentId);
        if (shipment != null) {
            System.out.println("Shipment " + shipment.getId() + " - Status: " + shipment.getStatus());
        } else {
            System.out.println("Shipment not found.");
        }
    }

    public void viewReports() {
        System.out.println("Total registered users: " + userService.listUsers().size());
    }

    public void checkOperationalStatus() {
        System.out.println("Checking the operational status of the system.");
    }
}
