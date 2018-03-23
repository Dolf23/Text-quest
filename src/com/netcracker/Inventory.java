package com.netcracker;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item getItemFromInventory(String itemName){
        for (Item item: items){
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public boolean isItemFromInventory(Item item){
        if (items.contains(item)){
            return true;
        }
        return false;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void deleteItemFromInventory(Item item){
        items.remove(item);
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
