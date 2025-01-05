package handlers;

import commands.CommandExecutor;
import parser.Parser;
import parser.ParserImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;


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
            while(true) {
                Parser parser = new ParserImpl();
                List<String> tokens = parser.parse(br);
                CommandExecutor commandExecutor = new CommandExecutor();
                String res = commandExecutor.execute(tokens);
                System.out.println("OUTPUT : " + res);
                outputStream.write(res.getBytes());
                outputStream.flush();
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
