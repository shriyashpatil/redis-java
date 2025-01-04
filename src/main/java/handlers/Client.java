package handlers;

import commands.CommandExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


public class Client implements Runnable{

    private Socket clientSocket;


    public Client(Socket socket){
        this.clientSocket = socket;
    }


    @Override
    public void run() {
        try {
            OutputStream outputStream = clientSocket.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            CommandExecutor commandExecutor = new CommandExecutor();
            String input;
            input = br.readLine();
            System.out.println("INPUT : "+input);
            String res = commandExecutor.execute(input,br);
            System.out.println("OUTPUT : "+res);
            outputStream.write(res.getBytes());
            outputStream.flush();
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
