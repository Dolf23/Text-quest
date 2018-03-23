package com.netcracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.netcracker.Constants.*;

public class PlayerClient {

    public void start() {
        try {
            Socket socket = new Socket(HOST, PORT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(new MessageReader(reader));
            executor.shutdown();

            System.out.println(CONNECTION_OPEN_MSG);
            System.out.print(ENTER_NAME);

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            new Game().start(inputReader.readLine());

            while(true) {
                String message = inputReader.readLine();
                System.out.println("> " + message);

                writer.println(message);
                writer.flush();
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
