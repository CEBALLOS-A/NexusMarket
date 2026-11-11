package com.nexusmarket.model;

public class Shipment {

    private int id;
    private int orderId;
    private String dispatchDate;
    private String deliveryDate;
    private String status;

    public Shipment() {
    }

    public Shipment(int id, int orderId, String dispatchDate, String deliveryDate, String status) {
        this.id = id;
        this.orderId = orderId;
        this.dispatchDate = dispatchDate;
        this.deliveryDate = deliveryDate;
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

    public String getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(String dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
