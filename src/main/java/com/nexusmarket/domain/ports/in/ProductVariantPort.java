package com.nexusmarket.domain.ports.in;

import com.nexusmarket.domain.model.ProductVariant;

public interface ProductVariantPort {

    void createVariant(ProductVariant variant);

    void updateVariant(int variantId, String description);

    void deleteVariant(int variantId);

    ProductVariant findVariant(int variantId);
}
