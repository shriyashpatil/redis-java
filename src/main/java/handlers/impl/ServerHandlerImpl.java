package handlers.impl;

import handlers.ServerHandler;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerHandlerImpl implements ServerHandler {


    private Integer serverPort;
    private ServerSocket serverSocket;
    private static ServerHandlerImpl serverHandler = null;
    private ServerHandlerImpl(){

    }


    public static ServerHandlerImpl getInstance(){
        if(serverHandler==null) {
            serverHandler = new ServerHandlerImpl();
        }
        return serverHandler;
    }



    @Override
    public void connect(Integer port) {
        try {
            setServerPort(port);
            serverSocket = new ServerSocket(port);
            setServerSocket(serverSocket);
            serverSocket.setReuseAddress(true);
            System.out.println("Server Initiated, Please PING !!!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    @Override
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }


}
