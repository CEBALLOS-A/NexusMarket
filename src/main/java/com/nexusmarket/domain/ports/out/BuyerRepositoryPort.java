package com.nexusmarket.domain.ports.out;

import com.nexusmarket.domain.model.Buyer;

public interface BuyerRepositoryPort {

    Buyer findById(int userId);

    void save(Buyer buyer);
}
