package com.nexusmarket.domain.ports.out;

import com.nexusmarket.domain.model.Refund;

import java.util.List;

public interface RefundRepositoryPort {

    Refund findById(int id);

    List<Refund> findAll();

    void save(Refund refund);
}
