package com.nexusmarket.model;

public class Order {

    private int id;
    private String buyer;
    private String status;
    private int quantity;
    private double price;
    private int productId;
    private double subtotal;

    public Order() {
    }

    public Order(int id, String buyer, String status, int quantity, double price,
                 int productId, double subtotal) {
        this.id = id;
        this.buyer = buyer;
        this.status = status;
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
        this.subtotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
