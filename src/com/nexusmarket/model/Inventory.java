package com.nexusmarket.model;

public class Inventory {

    private String product;
    private String warehouse;
    private int availableQuantity;
    private String stockStatus;

    public Inventory() {
    }

    public Inventory(String product, String warehouse, int availableQuantity, String stockStatus) {
        this.product = product;
        this.warehouse = warehouse;
        this.availableQuantity = availableQuantity;
        this.stockStatus = stockStatus;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }
}
