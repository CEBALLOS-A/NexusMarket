package com.nexusmarket.service;

import com.nexusmarket.model.Buyer;
import com.nexusmarket.model.Cart;
import com.nexusmarket.model.CartItem;
import com.nexusmarket.model.Order;
import com.nexusmarket.model.ProductReturn;

import java.util.ArrayList;
import java.util.List;

public class BuyerService {

    private final List<Buyer> buyers = new ArrayList<>();
    private final CartService cartService;
    private final OrderService orderService;
    private final ProductReturnService productReturnService;

    public BuyerService(CartService cartService, OrderService orderService,
                        ProductReturnService productReturnService) {
        this.cartService = cartService;
        this.orderService = orderService;
        this.productReturnService = productReturnService;
    }

    public void registerBuyer(Buyer buyer) {
        buyers.add(buyer);
        System.out.println("Buyer successfully registered.");
    }

    public Buyer findBuyer(int userId) {
        for (Buyer buyer : buyers) {
            if (buyer.getId() == userId) {
                return buyer;
            }
        }
        return null;
    }

    public void addAddress(int userId, String address) {
        Buyer buyer = findBuyer(userId);
        if (buyer != null) {
            buyer.setMainAddress(address);
            System.out.println("Address successfully added.");
        } else {
            System.out.println("Buyer not found.");
        }
    }

    public void updateAddress(int userId, String newAddress) {
        Buyer buyer = findBuyer(userId);
        if (buyer != null) {
            buyer.setMainAddress(newAddress);
            System.out.println("Address successfully updated.");
        } else {
            System.out.println("Buyer not found.");
        }
    }

    public void createCart(Cart cart) {
        cartService.registerCart(cart);
    }

    public void addProductToCart(CartItem item) {
        cartService.addProduct(item);
    }

    public void removeProductFromCart(int itemId) {
        cartService.removeProduct(itemId);
    }

    public void confirmOrder(Order order) {
        orderService.createOrder(order);
    }

    public Order findOrder(int orderId) {
        return orderService.findOrder(orderId);
    }

    public void requestReturn(ProductReturn productReturn) {
        productReturnService.requestReturn(productReturn);
    }
}
