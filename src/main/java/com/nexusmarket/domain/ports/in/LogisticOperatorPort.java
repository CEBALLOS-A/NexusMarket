package com.nexusmarket.domain.ports.in;

public interface LogisticOperatorPort {

    void prepareOrder(int orderId);

    void packOrder(int shipmentId);

    void dispatchOrder(int orderId, int shipmentId, String dispatchDate);

    void updateShipment(int shipmentId, String newStatus);

    void checkWarehouse(int warehouseId);
}
