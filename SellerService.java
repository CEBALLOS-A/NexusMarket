package com.nexusmarket.service;

import com.nexusmarket.model.Inventory;
import com.nexusmarket.model.Product;
import com.nexusmarket.model.Seller;

import java.util.ArrayList;
import java.util.List;

public class SellerService {

    private final List<Seller> sellers = new ArrayList<>();
    private final ProductService productService;
    private final InventoryService inventoryService;

    public SellerService(ProductService productService, InventoryService inventoryService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
    }

    public void registerSeller(Seller seller) {
        sellers.add(seller);
        System.out.println("Seller successfully registered.");
    }

    public void updateSeller(int userId, String fullName, String email) {
        Seller seller = findSeller(userId);
        if (seller != null) {
            seller.setFullName(fullName);
            seller.setEmail(email);
            System.out.println("Seller successfully updated.");
        } else {
            System.out.println("No seller found with that id.");
        }
    }

    public Seller findSeller(int userId) {
        for (Seller seller : sellers) {
            if (seller.getId() == userId) {
                return seller;
            }
        }
        return null;
    }

    public void manageProducts(int sellerId) {
        Seller seller = findSeller(sellerId);
        if (seller == null) {
            System.out.println("Seller not found.");
            return;
        }
        System.out.println("Seller " + seller.getFullName() + " is managing their products.");
    }

    public void registerProduct(Product product) {
        productService.registerProduct(product);
    }

    public void updateProduct(int productId, String name) {
        productService.updateProduct(productId, name);
    }

    public void suspendProduct(int productId) {
        productService.suspendProduct(productId);
    }

    public void checkInventory(String product, String warehouse) {
        Inventory inventory = inventoryService.findStock(product, warehouse);
        if (inventory != null) {
            System.out.println("Product: " + inventory.getProduct() + " - Quantity: " + inventory.getAvailableQuantity());
        } else {
            System.out.println("Inventory not found.");
        }
    }
}
