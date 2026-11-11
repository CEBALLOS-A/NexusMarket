package com.nexusmarket.domain.ports.out;

import com.nexusmarket.domain.model.ProductReturn;

import java.util.List;

public interface ReturnRepositoryPort {

    ProductReturn findById(int id);

    List<ProductReturn> findAll();

    void save(ProductReturn productReturn);
}
