package com.nexusmarket.domain.ports.in;

import com.nexusmarket.domain.model.InventoryMovement;

public interface InventoryMovementPort {

    void registerMovement(InventoryMovement movement);

    boolean validateMovement(InventoryMovement movement);

    void applyMovement(InventoryMovement movement);
}
