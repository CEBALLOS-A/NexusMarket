package com.nexusmarket.service;

import com.nexusmarket.model.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InvoiceService {

    private final List<Invoice> invoices = new ArrayList<>();

    public void generateInvoice(Invoice invoice) {
        if (validateInvoice(invoice)) {
            invoices.add(invoice);
            System.out.println("Invoice successfully generated.");
        } else {
            System.out.println("The invoice could not be generated, data is incomplete.");
        }
    }

    public Invoice findInvoice(int invoiceId) {
        for (Invoice invoice : invoices) {
            if (invoice.getId() == invoiceId) {
                return invoice;
            }
        }
        return null;
    }

    public boolean validateInvoice(Invoice invoice) {
        return invoice.getOrderId() != 0 && invoice.getAmount() > 0;
    }
}
