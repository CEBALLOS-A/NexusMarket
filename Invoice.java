package com.nexusmarket.model;

public class Invoice {

    private int id;
    private int orderId;
    private String date;
    private double amount;

    public Invoice() {
    }

    public Invoice(int id, int orderId, String date, double amount) {
        this.id = id;
        this.orderId = orderId;
        this.date = date;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
