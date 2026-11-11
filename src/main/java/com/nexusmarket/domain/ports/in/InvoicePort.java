package com.nexusmarket.domain.ports.in;

import com.nexusmarket.domain.model.Invoice;

public interface InvoicePort {

    void generateInvoice(Invoice invoice);

    Invoice findInvoice(int invoiceId);

    boolean validateInvoice(Invoice invoice);
}
