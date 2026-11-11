package com.nexusmarket.domain.model;

public enum EmployeeType {

    ADMINISTRATOR("Administrator"),
    SUPERVISOR("Supervisor"),
    SELLER("Seller"),
    LOGISTICS_OPERATOR("Logistics Operator"),
    BUYER("Buyer");

    private final String label;

    EmployeeType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
