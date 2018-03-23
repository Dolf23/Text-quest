package com.netcracker;

public class Item {
    private String name;
    private String description;
    private boolean moveable;

    public Item(String name, String description, boolean moveable) {
        this.name = name;
        this.description = description;
        this.moveable = moveable;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isMoveable() {
        return moveable;
    }

}
