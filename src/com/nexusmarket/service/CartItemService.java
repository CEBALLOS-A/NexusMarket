package com.nexusmarket.service;

import com.nexusmarket.model.CartItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CartItemService {

    private final List<CartItem> items = new ArrayList<>();

    public void registerItem(CartItem item) {
        items.add(item);
        System.out.println("Product added to the cart.");
    }

    public void deleteItem(int itemId) {
        CartItem item = findItem(itemId);
        if (item != null) {
            items.remove(item);
            System.out.println("Product removed from the cart.");
        } else {
            System.out.println("Product not found in the cart.");
        }
    }

    public void deleteByCart(int cartId) {
        Iterator<CartItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getCartId() == cartId) {
                iterator.remove();
            }
        }
    }

    public void updateQuantity(int itemId, int quantity) {
        CartItem item = findItem(itemId);
        if (item != null) {
            item.setQuantity(quantity);
            System.out.println("Quantity successfully updated.");
        } else {
            System.out.println("Product not found in the cart.");
        }
    }

    public double calculateSubtotal(int itemId) {
        CartItem item = findItem(itemId);
        if (item != null) {
            return item.getQuantity() * item.getUnitPrice();
        }
        return 0;
    }

    public double calculateTotalByCart(int cartId) {
        double total = 0;
        for (CartItem item : items) {
            if (item.getCartId() == cartId) {
                total += item.getQuantity() * item.getUnitPrice();
            }
        }
        return total;
    }

    private CartItem findItem(int itemId) {
        for (CartItem item : items) {
            if (item.getId() == itemId) {
                return item;
            }
        }
        return null;
    }
}
