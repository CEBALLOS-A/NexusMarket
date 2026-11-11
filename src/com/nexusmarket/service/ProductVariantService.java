package com.nexusmarket.service;

import com.nexusmarket.model.ProductVariant;

import java.util.ArrayList;
import java.util.List;

public class ProductVariantService {

    private final List<ProductVariant> variants = new ArrayList<>();

    public void createVariant(ProductVariant variant) {
        variants.add(variant);
        System.out.println("Variant successfully created.");
    }

    public void updateVariant(int variantId, String description) {
        ProductVariant variant = findVariant(variantId);
        if (variant != null) {
            variant.setDescription(description);
            System.out.println("Variant successfully updated.");
        } else {
            System.out.println("Variant not found.");
        }
    }

    public void deleteVariant(int variantId) {
        ProductVariant variant = findVariant(variantId);
        if (variant != null) {
            variants.remove(variant);
            System.out.println("Variant successfully deleted.");
        } else {
            System.out.println("Variant not found.");
        }
    }

    public ProductVariant findVariant(int variantId) {
        for (ProductVariant variant : variants) {
            if (variant.getId() == variantId) {
                return variant;
            }
        }
        return null;
    }
}
