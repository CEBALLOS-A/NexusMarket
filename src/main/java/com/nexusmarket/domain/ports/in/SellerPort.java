package com.nexusmarket.domain.ports.in;

import com.nexusmarket.domain.model.Product;
import com.nexusmarket.domain.model.Seller;

public interface SellerPort {

    void registerSeller(Seller seller);

    void updateSeller(int userId, String fullName, String email);

    Seller findSeller(int userId);

    void manageProducts(int sellerId);

    void registerProduct(Product product);

    void updateProduct(int productId, String name);

    void suspendProduct(int productId);

    void checkInventory(String product, String warehouse);
}
