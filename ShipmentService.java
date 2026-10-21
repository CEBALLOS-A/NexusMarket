package com.nexusmarket.service;

import com.nexusmarket.model.Shipment;

import java.util.ArrayList;
import java.util.List;

public class ShipmentService {

    private final List<Shipment> shipments = new ArrayList<>();

    public void prepareShipment(Shipment shipment) {
        shipment.setStatus("In Preparation");
        shipments.add(shipment);
        System.out.println("Shipment in preparation.");
    }

    public void pack(int shipmentId) {
        Shipment shipment = findShipment(shipmentId);
        if (shipment != null) {
            shipment.setStatus("Packed");
            System.out.println("Shipment successfully packed.");
        } else {
            System.out.println("Shipment not found.");
        }
    }

    public void dispatch(int shipmentId, String dispatchDate) {
        Shipment shipment = findShipment(shipmentId);
        if (shipment != null) {
            shipment.setDispatchDate(dispatchDate);
            shipment.setStatus("Dispatched");
            System.out.println("Shipment successfully dispatched.");
        } else {
            System.out.println("Shipment not found.");
        }
    }

    public void registerDelivery(int shipmentId, String deliveryDate) {
        Shipment shipment = findShipment(shipmentId);
        if (shipment != null) {
            shipment.setDeliveryDate(deliveryDate);
            shipment.setStatus("Delivered");
            System.out.println("Delivery successfully registered.");
        } else {
            System.out.println("Shipment not found.");
        }
    }

    public void updateStatus(int shipmentId, String newStatus) {
        Shipment shipment = findShipment(shipmentId);
        if (shipment != null) {
            shipment.setStatus(newStatus);
            System.out.println("Shipment status successfully updated.");
        } else {
            System.out.println("Shipment not found.");
        }
    }

    public Shipment findShipment(int shipmentId) {
        for (Shipment shipment : shipments) {
            if (shipment.getId() == shipmentId) {
                return shipment;
            }
        }
        return null;
    }
}
