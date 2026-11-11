package com.nexusmarket.domain.ports.out;

import com.nexusmarket.domain.model.Inventory;
import com.nexusmarket.domain.model.InventoryMovement;

import java.util.List;

public interface InventoryRepositoryPort {

    Inventory findByProductAndWarehouse(String product, String warehouse);

    List<Inventory> findByWarehouse(String warehouse);

    void save(Inventory inventory);

    void saveMovement(InventoryMovement movement);
}
