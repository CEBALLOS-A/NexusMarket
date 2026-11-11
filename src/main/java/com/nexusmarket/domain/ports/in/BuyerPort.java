package com.nexusmarket.domain.ports.in;

import com.nexusmarket.domain.model.Buyer;
import com.nexusmarket.domain.model.Cart;
import com.nexusmarket.domain.model.CartItem;
import com.nexusmarket.domain.model.Order;
import com.nexusmarket.domain.model.ProductReturn;

public interface BuyerPort {

    void registerBuyer(Buyer buyer);

    Buyer findBuyer(int userId);

    void addAddress(int userId, String address);

    void updateAddress(int userId, String newAddress);

    void createCart(Cart cart);

    void addProductToCart(CartItem item);

    void removeProductFromCart(int itemId);

    void confirmOrder(Order order);

    Order findOrder(int orderId);

    void requestReturn(ProductReturn productReturn);
}
