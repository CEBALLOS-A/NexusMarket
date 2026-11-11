package com.nexusmarket.service;

import com.nexusmarket.model.Warehouse;

public class LogisticsOperatorService {

    private final OrderService orderService;
    private final ShipmentService shipmentService;
    private final WarehouseService warehouseService;

    public LogisticsOperatorService(OrderService orderService, ShipmentService shipmentService,
                                    WarehouseService warehouseService) {
        this.orderService = orderService;
        this.shipmentService = shipmentService;
        this.warehouseService = warehouseService;
    }

    public void prepareOrder(int orderId) {
        orderService.validatePayment(orderId);
        System.out.println("Order in preparation.");
    }

    public void packOrder(int shipmentId) {
        shipmentService.pack(shipmentId);
    }

    public void dispatchOrder(int orderId, int shipmentId, String dispatchDate) {
        orderService.dispatchOrder(orderId);
        shipmentService.dispatch(shipmentId, dispatchDate);
    }

    public void updateShipment(int shipmentId, String newStatus) {
        shipmentService.updateStatus(shipmentId, newStatus);
    }

    public void checkWarehouse(int warehouseId) {
        Warehouse warehouse = warehouseService.findWarehouse(warehouseId);
        if (warehouse != null) {
            System.out.println("Warehouse: " + warehouse.getName());
        } else {
            System.out.println("Warehouse not found.");
        }
    }
}
