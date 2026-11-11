package com.nexusmarket.domain.ports.in;

import com.nexusmarket.domain.model.Refund;

public interface RefundPort {

    void processRefund(Refund refund);

    Refund findRefund(int refundId);
}
