import handlers.ClientHandler;
import handlers.ServerHandler;
import handlers.impl.ClientHandlerImpl;
import handlers.impl.ServerHandlerImpl;


public class Main {
  public static void main(String[] args){
        try {
            int port = 6379;
            ServerHandlerImpl server = ServerHandlerImpl.getInstance();
            server.connect(port);
            ClientHandler clientHandler = new ClientHandlerImpl(server);
            clientHandler.handle();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
  }
}
