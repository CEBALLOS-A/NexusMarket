package com.nexusmarket.enums;

public enum UserStatus {

    ACTIVE("Active"),
    INACTIVE("Inactive"),
    SUSPENDED("Suspended");

    private final String label;

    UserStatus(String label) {
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
