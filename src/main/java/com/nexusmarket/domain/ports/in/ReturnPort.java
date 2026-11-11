package com.nexusmarket.domain.ports.in;

import com.nexusmarket.domain.model.ProductReturn;

public interface ReturnPort {

    void requestReturn(ProductReturn productReturn);

    void registerReturn(ProductReturn productReturn);

    void approveReturn(int returnId);

    void rejectReturn(int returnId);

    ProductReturn findReturn(int returnId);
}
