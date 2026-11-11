package com.nexusmarket.model;

import com.nexusmarket.enums.EmployeeType;
import com.nexusmarket.enums.UserStatus;

public class Buyer extends User {

    private String mainAddress;
    private String commercialStatus;

    public Buyer() {
    }

    public Buyer(int id, String fullName, String email, EmployeeType role, UserStatus status,
                 String mainAddress, String commercialStatus) {
        super(id, fullName, email, role, status);
        this.mainAddress = mainAddress;
        this.commercialStatus = commercialStatus;
    }

    public String getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(String mainAddress) {
        this.mainAddress = mainAddress;
    }

    public String getCommercialStatus() {
        return commercialStatus;
    }

    public void setCommercialStatus(String commercialStatus) {
        this.commercialStatus = commercialStatus;
    }
}
