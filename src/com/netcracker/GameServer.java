package com.netcracker;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.netcracker.Constants.*;

public class GameServer {
    private List<PrintWriter> outputStreams;

    public void start() {

        outputStreams = new ArrayList<>();

        try {
            ExecutorService executor = Executors.newCachedThreadPool();
            ServerSocket serverSocket =  new ServerSocket(PORT);
            System.out.println(START_SERVER_MSG);

            while(true) {
                Socket socket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                outputStreams.add(writer);
                executor.execute(new ClientHandler(socket, outputStreams));
            }

//            executor.shutdown();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
