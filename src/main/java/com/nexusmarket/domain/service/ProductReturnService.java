package com.nexusmarket.domain.service;

import com.nexusmarket.domain.ports.in.ReturnPort;

import com.nexusmarket.domain.model.ProductReturn;

import java.util.ArrayList;
import java.util.List;

public class ProductReturnService implements ReturnPort {

    private final List<ProductReturn> returns = new ArrayList<>();

    public void requestReturn(ProductReturn productReturn) {
        productReturn.setStatus("Requested");
        returns.add(productReturn);
        System.out.println("Return successfully requested.");
    }

    public void registerReturn(ProductReturn productReturn) {
        returns.add(productReturn);
        System.out.println("Return successfully registered.");
    }

    public void approveReturn(int returnId) {
        changeStatus(returnId, "Approved");
    }

    public void rejectReturn(int returnId) {
        changeStatus(returnId, "Rejected");
    }

    public ProductReturn findReturn(int returnId) {
        for (ProductReturn productReturn : returns) {
            if (productReturn.getId() == returnId) {
                return productReturn;
            }
        }
        return null;
    }

    private void changeStatus(int returnId, String newStatus) {
        ProductReturn productReturn = findReturn(returnId);
        if (productReturn != null) {
            productReturn.setStatus(newStatus);
            System.out.println("Return status changed to " + newStatus + ".");
        } else {
            System.out.println("Return not found.");
        }
    }
}
