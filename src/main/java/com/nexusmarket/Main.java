package com.nexusmarket;

import com.nexusmarket.domain.model.*;
import com.nexusmarket.domain.service.*;

/**
 * Demo that runs the full NexusMarket flow: users, products, inventory,
 * cart, order, shipment, invoice, return and refund.
 */
public class Main {

    public static void main(String[] args) {
        // Wiring of the services
        UserService userService = new UserService();
        ProductVariantService variantService = new ProductVariantService();
        ProductService productService = new ProductService(variantService);
        InventoryService inventoryService = new InventoryService();
        InventoryMovementService movementService = new InventoryMovementService(inventoryService);
        WarehouseService warehouseService = new WarehouseService(inventoryService);
        CartItemService cartItemService = new CartItemService();
        CartService cartService = new CartService(cartItemService);
        OrderService orderService = new OrderService();
        ShipmentService shipmentService = new ShipmentService();
        InvoiceService invoiceService = new InvoiceService();
        ProductReturnService returnService = new ProductReturnService();
        RefundService refundService = new RefundService();

        SellerService sellerService = new SellerService(productService, inventoryService);
        BuyerService buyerService = new BuyerService(cartService, orderService, returnService);
        LogisticsOperatorService logisticsService =
                new LogisticsOperatorService(orderService, shipmentService, warehouseService);
        AdministratorService administratorService = new AdministratorService(
                sellerService, warehouseService, userService, orderService, inventoryService, shipmentService);

        section("Users");
        Administrator admin = new Administrator(1, "Ana Admin", "ana@nexus.com", EmployeeType.ADMINISTRATOR, UserStatus.ACTIVE);
        Administrator supervisor = new Administrator(2, "Sam Supervisor", "sam@nexus.com", EmployeeType.SUPERVISOR, UserStatus.ACTIVE);
        Seller seller = new Seller(3, "Carlos Seller", "carlos@nexus.com", EmployeeType.SELLER, UserStatus.ACTIVE);
        Buyer buyer = new Buyer(4, "Laura Buyer", "laura@nexus.com", EmployeeType.BUYER, UserStatus.ACTIVE, "10 Main Street", "Regular");
        userService.registerUser(admin);
        userService.registerUser(supervisor);
        userService.registerUser(seller);
        userService.registerUser(buyer);
        administratorService.registerSeller(seller);
        buyerService.registerBuyer(buyer);
        administratorService.viewUsers();
        administratorService.viewReports();

        section("Warehouse and products");
        administratorService.addWarehouse(new Warehouse(1, "Main Warehouse"));
        Product laptop = new Product("Laptop", "Electronics", ProductStatus.DRAFT);
        sellerService.registerProduct(laptop);
        warehouseService.associateProduct(1, laptop.getId());
        productService.publishProduct(laptop.getId());
        productService.addVariant(laptop.getId(), new ProductVariant(1, laptop.getId(), "16GB RAM"));
        sellerService.manageProducts(3);

        section("Inventory");
        movementService.applyMovement(new InventoryMovement("Incoming", 10, "2026-10-20", "Laptop", 1));
        warehouseService.queryInventory(1);
        administratorService.viewInventory("Laptop", "1");
        sellerService.checkInventory("Laptop", "1");

        section("Cart");
        buyerService.createCart(new Cart(1, "Laura Buyer", "Open"));
        buyerService.addProductToCart(new CartItem(1, "Laura Buyer", "Active", "2026-10-20", 1, "Laptop", 2, 2500000));
        System.out.println("Cart total: " + cartService.calculateTotal(1));
        cartService.confirmPurchase(1);

        section("Order, shipment and invoice");
        buyerService.confirmOrder(new Order(1, "Laura Buyer", "", 2, 2500000, laptop.getId(), 5000000));
        orderService.confirmOrder(1);
        inventoryService.reserveInventory("Laptop", "1", 2);
        logisticsService.prepareOrder(1);
        shipmentService.prepareShipment(new Shipment(1, 1, null, null, ""));
        logisticsService.packOrder(1);
        logisticsService.dispatchOrder(1, 1, "2026-10-21");
        shipmentService.registerDelivery(1, "2026-10-23");
        orderService.confirmDelivery(1);
        invoiceService.generateInvoice(new Invoice(1, 1, "2026-10-23", 5000000));
        orderService.completeOrder(1);
        administratorService.viewOrder(1);
        administratorService.viewShipment(1);

        section("Return and refund");
        buyerService.requestReturn(new ProductReturn(1, 1, "2026-10-25", "Defective product", ""));
        returnService.approveReturn(1);
        refundService.processRefund(new Refund(1, 1, "2026-10-26", 5000000, ""));
        movementService.applyMovement(new InventoryMovement("Return", 2, "2026-10-26", "Laptop", 1));
        administratorService.viewInventory("Laptop", "1");
        administratorService.checkOperationalStatus();
    }

    private static void section(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }
}
