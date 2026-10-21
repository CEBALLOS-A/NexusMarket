package com.nexusmarket.model;

public class CartItem {

    private int id;
    private String buyer;
    private String status;
    private String date;
    private int cartId;
    private String product;
    private int quantity;
    private double unitPrice;

    public CartItem() {
    }

    public CartItem(int id, String buyer, String status, String date, int cartId,
                    String product, int quantity, double unitPrice) {
        this.id = id;
        this.buyer = buyer;
        this.status = status;
        this.date = date;
        this.cartId = cartId;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
