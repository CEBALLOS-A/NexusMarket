package com.nexusmarket.domain.ports.out;

import com.nexusmarket.domain.model.Warehouse;

import java.util.List;

public interface WarehouseRepositoryPort {

    Warehouse findById(int id);

    List<Warehouse> findAll();

    void save(Warehouse warehouse);
}
