package com.nexusmarket.model;

public class Product {

    private int id;
    private String name;
    private String productType;
    private String status;

    public Product() {
    }

    public Product(int id, String name, String productType, String status) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
