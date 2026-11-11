package com.nexusmarket.enums;

public enum DocumentType {

    CITIZEN_ID("Citizen ID"),
    FOREIGN_ID("Foreign ID"),
    PASSPORT("Passport"),
    TAX_ID("Tax ID");

    private final String label;

    DocumentType(String label) {
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
