package handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

public class ClientHandler implements  Runnable{
    private final Socket clientSocket;

    private HashMap<String ,String> cache = new HashMap<>();

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
                else if (input.equalsIgnoreCase("ECHO")){
                    String echo = br.readLine();
                    echo = br.readLine();
                    outputStream.write( String.format("$%d\r\n%s\r\n",echo.length(), echo).getBytes());
                    outputStream.flush();
                }
                else if (input.equalsIgnoreCase("SET")){
                    String key = br.readLine();
                    key = br.readLine();
                    String value = br.readLine();
                    value = br.readLine();
                    cache.put(key,value);
                    outputStream.write( String.format("$%d\r\n%s\r\n","OK".length(), "OK").getBytes());
                    outputStream.flush();
                }
                else if(input.equalsIgnoreCase("GET")){
                    String key = br.readLine();
                    key = br.readLine();
                    String value = cache.get(key);
                    outputStream.write( String.format("$%d\r\n%s\r\n",value.length(),value).getBytes());
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
