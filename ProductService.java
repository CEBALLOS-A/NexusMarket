package com.nexusmarket.service;

import com.nexusmarket.model.Product;
import com.nexusmarket.model.ProductVariant;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private final ProductVariantService variantService;

    public ProductService(ProductVariantService variantService) {
        this.variantService = variantService;
    }

    public void registerProduct(Product product) {
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
        changeProductStatus(productId, "Published");
    }

    public void suspendProduct(int productId) {
        changeProductStatus(productId, "Suspended");
    }

    public void discontinueProduct(int productId) {
        changeProductStatus(productId, "Discontinued");
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

    public Product findProduct(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    private void changeProductStatus(int productId, String newStatus) {
        Product product = findProduct(productId);
        if (product != null) {
            product.setStatus(newStatus);
            System.out.println("Product status changed to " + newStatus + ".");
        } else {
            System.out.println("Product not found.");
        }
    }
}
