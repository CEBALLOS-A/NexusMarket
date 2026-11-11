package com.nexusmarket.model;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private int id;
    private String name;
    private final List<Integer> associatedProducts = new ArrayList<>();

    public Warehouse() {
    }

    public Warehouse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getAssociatedProducts() {
        return associatedProducts;
    }
}
