package com.nexusmarket.service;

import com.nexusmarket.model.Refund;

import java.util.ArrayList;
import java.util.List;

public class RefundService {

    private final List<Refund> refunds = new ArrayList<>();

    public void processRefund(Refund refund) {
        refund.setStatus("Processed");
        refunds.add(refund);
        System.out.println("Refund successfully processed.");
    }

    public Refund findRefund(int refundId) {
        for (Refund refund : refunds) {
            if (refund.getId() == refundId) {
                return refund;
            }
        }
        return null;
    }
}
