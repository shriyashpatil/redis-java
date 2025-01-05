package commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class EchoCommand implements Command{

    private final String ECHO_COMMAND = "ECHO";

    @Override
    public String execute(List<String> tokens) {
        String echo = tokens.get(1);
        return String.format("$%d\r\n%s\r\n",echo.length(), echo);
    }

    @Override
    public boolean mathces(String input) {
        return input.equalsIgnoreCase(ECHO_COMMAND);
    }
}
