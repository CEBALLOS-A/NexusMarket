package com.nexusmarket.model;

public class ProductReturn {

    private int id;
    private int orderId;
    private String date;
    private String reason;
    private String status;

    public ProductReturn() {
    }

    public ProductReturn(int id, int orderId, String date, String reason, String status) {
        this.id = id;
        this.orderId = orderId;
        this.date = date;
        this.reason = reason;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
