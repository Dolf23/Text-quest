package com.netcracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {

    private List<PrintWriter> outputStreams;
    private BufferedReader reader;

    public ClientHandler(Socket socket, List<PrintWriter> outputStreams) {
        this.outputStreams = outputStreams;

        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        String message;

        try {
            while((message = reader.readLine()) != null) {
                System.out.println(message);
                sendMessage(message);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private synchronized void sendMessage(String message) {
        for(PrintWriter writer : outputStreams) {
            writer.println(message);
            writer.flush();
        }
    }
}
