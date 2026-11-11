package com.nexusmarket.domain.model;

public enum ProductStatus {

    DRAFT("Draft"),
    PUBLISHED("Published"),
    SUSPENDED("Suspended"),
    DISCONTINUED("Discontinued");

    private final String label;

    ProductStatus(String label) {
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
