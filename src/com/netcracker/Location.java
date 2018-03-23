package com.netcracker;

import java.util.ArrayList;

public class Location {
    private String name;
    private String description;
    private Inventory inventory;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        inventory = new Inventory();
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
        return description + " Здесь находятся: " + inventory;
    }

}
