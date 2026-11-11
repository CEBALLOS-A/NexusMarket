package com.nexusmarket.domain.ports.out;

import com.nexusmarket.domain.model.Shipment;

public interface ShipmentRepositoryPort {

    Shipment findById(int id);

    void save(Shipment shipment);
}
