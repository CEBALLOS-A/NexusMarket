package com.nexusmarket.domain.ports.out;

import com.nexusmarket.domain.model.Cart;

public interface CartRepositoryPort {

    Cart findById(int id);

    void save(Cart cart);
}
