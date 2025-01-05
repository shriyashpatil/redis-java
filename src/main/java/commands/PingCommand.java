package commands;

import java.io.BufferedReader;
import java.util.List;

public class PingCommand implements Command{

    private final String PING_COOMAND = "PING";
    private final String PING_RESPONSE = "+PONG\r\n";

    @Override
    public String execute(List<String> tokens) {
        return "+PONG\r\n";
    }

    @Override
    public boolean mathces(String input) {
        return input.equalsIgnoreCase(PING_COOMAND);
    }
}
