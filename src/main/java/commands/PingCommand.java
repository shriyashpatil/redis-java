package commands;

import java.io.BufferedReader;

public class PingCommand implements Command{

    private final String PING_COOMAND = "PING";
    private final String PING_RESPONSE = "+PONG\r\n";

    @Override
    public String execute(String input, BufferedReader br) {
        return "+PONG\r\n";
    }

    @Override
    public boolean mathces(String input) {
        return input.equalsIgnoreCase(PING_COOMAND);
    }
}
