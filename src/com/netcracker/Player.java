package com.netcracker;

import java.util.HashSet;
import java.util.Set;

import static com.netcracker.Constants.*;

public class Player {
    private String name;
    private Location location;
    private Inventory inventory;

    public Player(String name){
        this.name = name;
        inventory = new Inventory();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

    public void changeLocation(HashSet<Way> ways, String direction){
        boolean is = false;
        for (Way way : ways){
            if(way.getLocationA().equals(location) && way.getDirection().equals(direction)){
                location = way.getLocationB();
                System.out.println(location.toString());
                is = true;
            }
        }
        if (!is){
            System.out.println(WRONG_WAY);
        }
    }

    public void takeItemFromLocation(String itemName) {
        Item item = location.getInventory().getItemFromInventory(itemName);
        if (item != null){
            if (item.isMoveable()){
                inventory.addItem(item);
                location.getInventory().deleteItemFromInventory(item);
                System.out.println(YOU_HAVE + item.getName());
            }
            else{
                System.out.println(item.getName() + WRONG_TAKE);
            }
        }
        else {
            System.out.println(WRONG_SUBJECT);
        }
    }

    public void showInventory(){
        if (inventory.getItems().isEmpty()){
            System.out.println(EMPTY_INVENTORY);
        }
        else{
            System.out.println(inventory.toString());
        }
    }


    public void craftItem(String object, String subject, Set<CraftingItem> craftingItems) {
        Item itemA = inventory.getItemFromInventory(object);
        Item itemB = inventory.getItemFromInventory(subject);
        boolean isLocationItem = false;
        if (itemB == null){
            itemB = location.getInventory().getItemFromInventory(subject);
            isLocationItem = true;
            if (itemB != null && itemB.isMoveable()){
                itemB = null;
            }
        }
        if (itemA != null){
            if (itemB != null){
                CraftingItem craftingItem = getCraftingItem(itemA, itemB, location, craftingItems);
                if (!isLocationItem){
                    if (craftingItem != null){
                        inventory.addItem(craftingItem.getResultItem());
                        inventory.deleteItemFromInventory(craftingItem.getObject());
                        inventory.deleteItemFromInventory(craftingItem.getSubject());
                        System.out.println(craftingItem.getMessage());
                    }
                    else{
                        System.out.println(WRONG_ACT);
                    }
                }
                else {
                    if (craftingItem != null){
                        inventory.deleteItemFromInventory(craftingItem.getObject());
                        inventory.addItem(craftingItem.getResultItem());
                        System.out.println(craftingItem.getMessage());
                    }
                    else{
                        System.out.println(WRONG_ACT);
                    }
                }
            }
            else {
                System.out.println(subject + DONT_HAVE);
            }
        }
        else{
            System.out.println(object + DONT_HAVE);
        }
    }


    private CraftingItem getCraftingItem(Item object, Item subject, Location location, Set<CraftingItem> craftingItems){
        for (CraftingItem craftingItem: craftingItems){
            if (object.equals(craftingItem.getObject()) && subject.equals(craftingItem.getSubject())){
                if (location.equals(craftingItem.getLocation()) || craftingItem.getLocation() == null){
                    return craftingItem;
                }
            }
        }
        return null;
    }
}
