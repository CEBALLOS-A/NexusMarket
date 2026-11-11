package com.nexusmarket.model;

public class Cart {

    private int id;
    private String buyer;
    private String status;

    public Cart() {
    }

    public Cart(int id, String buyer, String status) {
        this.id = id;
        this.buyer = buyer;
        this.status = status;
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
}
