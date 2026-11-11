package com.nexusmarket.service;

import com.nexusmarket.enums.ProductStatus;
import com.nexusmarket.model.Product;
import com.nexusmarket.model.ProductVariant;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private final ProductVariantService variantService;
    private int nextId = 1;

    public ProductService(ProductVariantService variantService) {
        this.variantService = variantService;
    }

    public void registerProduct(Product product) {
        product.setId(nextId++);
        products.add(product);
        System.out.println("Product successfully registered.");
    }

    public void updateProduct(int productId, String name) {
        Product product = findProduct(productId);
        if (product != null) {
            product.setName(name);
            System.out.println("Product successfully updated.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void publishProduct(int productId) {
        changeProductStatus(productId, ProductStatus.PUBLISHED);
    }

    public void suspendProduct(int productId) {
        changeProductStatus(productId, ProductStatus.SUSPENDED);
    }

    public void discontinueProduct(int productId) {
        changeProductStatus(productId, ProductStatus.DISCONTINUED);
    }

    public void addVariant(int productId, ProductVariant variant) {
        Product product = findProduct(productId);
        if (product != null) {
            variant.setProductId(productId);
            variantService.createVariant(variant);
        } else {
            System.out.println("Product not found.");
        }
    }

    public void updateVariant(int variantId, String description) {
        variantService.updateVariant(variantId, description);
    }

    public List<Product> listProducts() {
        return products;
    }

    public Product findProduct(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    private void changeProductStatus(int productId, ProductStatus newStatus) {
        Product product = findProduct(productId);
        if (product != null) {
            product.setStatus(newStatus);
            System.out.println("Product status changed to " + newStatus + ".");
        } else {
            System.out.println("Product not found.");
        }
    }
}
