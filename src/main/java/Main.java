import handlers.ClientHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  public static void main(String[] args){
    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.out.println("Logs from your program will appear here!");


        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        int port = 6379;

        try {
          ExecutorService executor = Executors.newFixedThreadPool(10);
          serverSocket = new ServerSocket(port);
          // Since the tester restarts your program quite often, setting SO_REUSEADDR
          // ensures that we don't run into 'Address already in use' errors
          serverSocket.setReuseAddress(true);
          // Wait for connection from client.
          // multiple client
           while(true) {
               clientSocket = serverSocket.accept();
              executor.submit(new ClientHandler(clientSocket));
           }

        } catch (IOException e) {
          System.out.println("IOException: " + e.getMessage());
        } finally {
          try {
            if (clientSocket != null) {
              clientSocket.close();
            }
          } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
          }
        }
  }
}
