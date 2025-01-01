package handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler implements  Runnable{
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            OutputStream outputStream = clientSocket.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String input;
            while (true) {
                input = br.readLine();
                if (input.trim().equalsIgnoreCase("PING")) {
                    outputStream.write("+PONG\r\n".getBytes());
                    outputStream.flush();
                }
            }
        }catch(IOException io){
            io.printStackTrace();
        }finally {

            try{
                clientSocket.close();
            }catch(IOException io){
                io.printStackTrace();
            }
        }

    }
}
