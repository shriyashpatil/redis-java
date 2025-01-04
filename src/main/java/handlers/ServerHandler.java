package handlers;

import java.net.ServerSocket;

public interface ServerHandler {

    public void connect(Integer port);

    public ServerSocket getServerSocket();

    public Integer getServerPort();
}
