package handlers.impl;

import handlers.ClientHandler;
import handlers.ServerHandler;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientHandlerImpl implements  ClientHandler {


    ExecutorService executor = Executors.newFixedThreadPool(10);

    ServerHandler serverHandler;

    public ClientHandlerImpl(ServerHandler serverHandler){
        this.serverHandler = serverHandler;
    }

    @Override
    public void handle() {
        int clientCounter =0;
        while(true){
            try {
                System.out.println("Server listening to port "+serverHandler.getServerPort()+"...");
                Socket clientSocket = serverHandler.getServerSocket().accept();
                System.out.println("client"+clientCounter+" connected");
                executor.submit(new Client(clientSocket));
                clientCounter++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
