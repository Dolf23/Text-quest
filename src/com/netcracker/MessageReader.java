package com.netcracker;

import java.io.BufferedReader;
import java.io.IOException;

public class MessageReader implements Runnable {
    private final BufferedReader reader;

    public MessageReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {
        String message;

        try {
            while((message = reader.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
