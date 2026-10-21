package com.nexusmarket.model;

public class ProductVariant {

    private int id;
    private int productId;
    private String description;

    public ProductVariant() {
    }

    public ProductVariant(int id, int productId, String description) {
        this.id = id;
        this.productId = productId;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
