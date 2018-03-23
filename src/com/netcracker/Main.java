package com.netcracker;

public class Main {

    public static void main(String[] args) {
        new GameServer().start();
        new PlayerClient().start();
    }

}