package com.nexusmarket.domain.ports.in;

import com.nexusmarket.domain.model.Product;
import com.nexusmarket.domain.model.ProductVariant;

import java.util.List;

public interface CatalogPort {

    void registerProduct(Product product);

    void updateProduct(int productId, String name);

    void publishProduct(int productId);

    void suspendProduct(int productId);

    void discontinueProduct(int productId);

    void addVariant(int productId, ProductVariant variant);

    void updateVariant(int variantId, String description);

    List<Product> listProducts();

    Product findProduct(int productId);
}
