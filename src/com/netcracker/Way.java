package com.netcracker;

public class Way {
    private Location locationA;
    private String direction;
    private Location locationB;

    public Location getLocationA() {
        return locationA;
    }

    public String getDirection() {
        return direction;
    }

    public Location getLocationB() {
        return locationB;
    }

    public Way(Location locationA, String direction, Location locationB) {
        this.locationA = locationA;
        this.direction = direction;
        this.locationB = locationB;
    }

}
