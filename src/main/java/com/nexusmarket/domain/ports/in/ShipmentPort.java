package com.nexusmarket.domain.ports.in;

import com.nexusmarket.domain.model.Shipment;

public interface ShipmentPort {

    void prepareShipment(Shipment shipment);

    void pack(int shipmentId);

    void dispatch(int shipmentId, String dispatchDate);

    void registerDelivery(int shipmentId, String deliveryDate);

    void updateStatus(int shipmentId, String newStatus);

    Shipment findShipment(int shipmentId);
}
