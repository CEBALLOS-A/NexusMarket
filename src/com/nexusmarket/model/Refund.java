package com.nexusmarket.model;

public class Refund {

    private int id;
    private int orderId;
    private String date;
    private double amount;
    private String status;

    public Refund() {
    }

    public Refund(int id, int orderId, String date, double amount, String status) {
        this.id = id;
        this.orderId = orderId;
        this.date = date;
        this.amount = amount;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
