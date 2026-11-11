package com.nexusmarket.domain.ports.in;

import com.nexusmarket.domain.model.Cart;
import com.nexusmarket.domain.model.CartItem;

public interface CartPort {

    void registerCart(Cart cart);

    Cart findCart(int cartId);

    void addProduct(CartItem item);

    void removeProduct(int itemId);

    void updateQuantity(int itemId, int quantity);

    void emptyCart(int cartId);

    double calculateTotal(int cartId);

    void confirmPurchase(int cartId);
}
