package com.nexusmarket.domain.ports.in;

import com.nexusmarket.domain.model.CartItem;

public interface CartItemPort {

    void registerItem(CartItem item);

    void deleteItem(int itemId);

    void deleteByCart(int cartId);

    void updateQuantity(int itemId, int quantity);

    double calculateSubtotal(int itemId);

    double calculateTotalByCart(int cartId);
}
