package com.nexusmarket.domain.ports.out;

import com.nexusmarket.domain.model.Seller;

public interface SellerRepositoryPort {

    Seller findById(int userId);

    void save(Seller seller);
}
