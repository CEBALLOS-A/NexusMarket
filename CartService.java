package com.nexusmarket.service;

import com.nexusmarket.model.Cart;
import com.nexusmarket.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartService {

    private final List<Cart> carts = new ArrayList<>();
    private final CartItemService cartItemService;

    public CartService(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    public void registerCart(Cart cart) {
        carts.add(cart);
        System.out.println("Cart successfully created.");
    }

    public Cart findCart(int cartId) {
        for (Cart cart : carts) {
            if (cart.getId() == cartId) {
                return cart;
            }
        }
        return null;
    }

    public void addProduct(CartItem item) {
        cartItemService.registerItem(item);
    }

    public void removeProduct(int itemId) {
        cartItemService.deleteItem(itemId);
    }

    public void updateQuantity(int itemId, int quantity) {
        cartItemService.updateQuantity(itemId, quantity);
    }

    public void emptyCart(int cartId) {
        cartItemService.deleteByCart(cartId);
        System.out.println("Cart successfully emptied.");
    }

    public double calculateTotal(int cartId) {
        return cartItemService.calculateTotalByCart(cartId);
    }

    public void confirmPurchase(int cartId) {
        Cart cart = findCart(cartId);
        if (cart != null) {
            cart.setStatus("Confirmed");
            System.out.println("Purchase successfully confirmed.");
        } else {
            System.out.println("Cart not found.");
        }
    }
}
