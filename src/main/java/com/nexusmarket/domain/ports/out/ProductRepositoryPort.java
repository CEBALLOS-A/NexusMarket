package com.nexusmarket.domain.ports.out;

import com.nexusmarket.domain.model.Product;
import com.nexusmarket.domain.model.ProductStatus;

import java.util.List;

public interface ProductRepositoryPort {

    Product findById(int id);

    List<Product> findAll();

    List<Product> findBySeller(int sellerId);

    List<Product> findByStatus(ProductStatus status);

    void save(Product product);

    void update(Product product);
}
