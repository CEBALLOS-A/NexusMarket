package com.nexusmarket.domain.model;

import com.nexusmarket.domain.model.ProductStatus;

public class Product {

    private int id;
    private String name;
    private String productType;
    private ProductStatus status;

    public Product() {
    }

    public Product(String name, String productType, ProductStatus status) {
        this.name = name;
        this.productType = productType;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }
}
