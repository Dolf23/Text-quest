package com.netcracker;

public class CraftingItem {
    private Item object;
    private Item subject;
    private Location location;
    private Item resultItem;
    private String message;

    public CraftingItem(Item object, Item subject, Item resultItem, String message) {
        this.object = object;
        this.subject = subject;
        this.resultItem = resultItem;
        this.message = message;
    }

    public CraftingItem(Item object, Item subject, Location location, Item resultItem, String message) {
        this.object = object;
        this.subject = subject;
        this.location = location;
        this.resultItem = resultItem;
        this.message = message;
    }

    public Item getObject() {
        return object;
    }

    public Item getSubject() {
        return subject;
    }

    public Location getLocation() {
        return location;
    }

    public Item getResultItem() {
        return resultItem;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "CraftingItem{" +
                "object=" + object +
                ", subject=" + subject +
                ", location=" + location +
                ", resultItem=" + resultItem +
                ", message='" + message + '\'' +
                '}';
    }
}
