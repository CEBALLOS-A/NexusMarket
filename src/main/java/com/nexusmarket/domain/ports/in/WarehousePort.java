package com.nexusmarket.domain.ports.in;

import com.nexusmarket.domain.model.Warehouse;

public interface WarehousePort {

    void addWarehouse(Warehouse warehouse);

    void updateWarehouse(int warehouseId, String name);

    Warehouse findWarehouse(int warehouseId);

    void queryInventory(int warehouseId);

    void associateProduct(int warehouseId, int productId);
}
