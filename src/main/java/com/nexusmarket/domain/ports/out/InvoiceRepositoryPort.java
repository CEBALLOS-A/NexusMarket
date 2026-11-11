package com.nexusmarket.domain.ports.out;

import com.nexusmarket.domain.model.Invoice;

public interface InvoiceRepositoryPort {

    Invoice findById(int id);

    void save(Invoice invoice);
}
