package com.nexusmarket.model;

public class InventoryMovement {

    private String movementType;
    private int quantity;
    private String date;
    private String product;
    private int warehouseId;

    public InventoryMovement() {
    }

    public InventoryMovement(String movementType, int quantity, String date, String product, int warehouseId) {
        this.movementType = movementType;
        this.quantity = quantity;
        this.date = date;
        this.product = product;
        this.warehouseId = warehouseId;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }
}
