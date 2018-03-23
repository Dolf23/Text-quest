package com.netcracker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static com.netcracker.Constants.*;

public class Game {
    private HashMap<String, Location> locations = new HashMap<>();;
    private HashSet<Way> ways = new HashSet<>();
    private HashMap<String, Item> items = new HashMap<>();
    private Set<CraftingItem> craftingItems = new HashSet<>();
    private Player player;
    private Scanner scanner = new Scanner(System.in);

    public void start(String name) {

        boolean isRunning = true;
        createWorld();
        System.out.print(ENTER_NAME);
        player = new Player(scanner.next());
        player.setLocation(locations.get(LIVING_ROOM));
        System.out.println(locations.get(LIVING_ROOM).toString());

        while (isRunning){
            System.out.print("> ");
            String command = scanner.next();
            String object;
            String subject;

            switch(command){
                case EXIT_CMD:
                    isRunning = false;
                    System.out.println(player.getName() + EXIT_MESS);
                    break;
                case GO_CMD:
                    player.changeLocation(ways, scanner.next());
                    break;
                case TAKE_CMD:
                    player.takeItemFromLocation(scanner.next());
                    break;
                case LOOK_AROUD_CMD:
                    System.out.println(player.getLocation().toString());
                    break;
                case INVENTORY_CMD:
                    player.showInventory();
                    break;
                case USE_CMD:
                    object = scanner.next();
                    scanner.next();
                    subject = scanner.next();
                    player.craftItem(object, subject, craftingItems);
                    break;
                default:
                    System.out.println(WRONG_COMMAND);
            }

            if (player.getInventory().isItemFromInventory(items.get(PRIZE))){
                isRunning = false;
            }

        }
    }

    private void createWorld(){

        locations.put(LIVING_ROOM, new Location(LIVING_ROOM,LIVING_ROOM_DESC));
        locations.put(ATTIC, new Location(ATTIC, ATTIC_DESC));
        locations.put(GARDEN, new Location(GARDEN, GARDEN_DESC));

        ways.add(new Way(locations.get(LIVING_ROOM), WEST, locations.get(GARDEN)));
        ways.add(new Way(locations.get(GARDEN), EAST, locations.get(LIVING_ROOM)));
        ways.add(new Way(locations.get(LIVING_ROOM), UP, locations.get(ATTIC)));
        ways.add(new Way(locations.get(ATTIC), DOWN, locations.get(LIVING_ROOM)));

        items.put(WIZARD, new Item(WIZARD, WIZARD_DESC, false));
        items.put(WHISKEY, new Item(WHISKEY, WHISKEY_DESC, true));
        items.put(BUCKET, new Item(BUCKET, BUCKET_DESC, true));
        items.put(BURNER, new Item(BURNER, BURNER_DESC, false));
        items.put(WELL, new Item(WELL, WELL_DESC, false));
        items.put(CHAIN, new Item(CHAIN, CHAIN_DESC,true));
        items.put(FROG, new Item(FROG, FROG_DESC, true));
        items.put(BUCKET_WITH_CHAIN, new Item(BUCKET, BUCKET_WITH_CHAIN_DESC, true));
        items.put(BUCKET_WITH_WATER, new Item(BUCKET, BUCKET_WITH_WATER_DESC, true));
        items.put(PRIZE, new Item(PRIZE,PRIZE_DESC, true));

        locations.get(LIVING_ROOM).getInventory().addItem(items.get(WHISKEY));
        locations.get(LIVING_ROOM).getInventory().addItem(items.get(BUCKET));
        locations.get(LIVING_ROOM).getInventory().addItem(items.get(WIZARD));
        locations.get(ATTIC).getInventory().addItem(items.get(BURNER));
        locations.get(GARDEN).getInventory().addItem(items.get(WELL));
        locations.get(GARDEN).getInventory().addItem(items.get(CHAIN));
        locations.get(GARDEN).getInventory().addItem(items.get(FROG));

        craftingItems.add(new CraftingItem(items.get(CHAIN), items.get(BUCKET), locations.get(ATTIC), items.get(BUCKET_WITH_CHAIN), BUCKET_WITH_CHAIN_MESS));
        craftingItems.add(new CraftingItem(items.get(BUCKET_WITH_CHAIN), items.get(WELL), locations.get(GARDEN), items.get(BUCKET_WITH_WATER), BUCKET_WITH_WATER_MESS));
        craftingItems.add(new CraftingItem(items.get(BUCKET_WITH_WATER), items.get(WIZARD), locations.get(LIVING_ROOM),items.get(PRIZE), CONGRATULATION_MESS));

    }
}
