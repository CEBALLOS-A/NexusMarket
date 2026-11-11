package com.nexusmarket.domain.ports.out;

public interface LogisticsPort {

    void dispatchShipment(int shipmentId, String dispatchDate);

    void registerDelivery(int shipmentId, String deliveryDate);

    void updateShipmentStatus(int shipmentId, String status);
}
