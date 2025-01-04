package commands;

import java.io.BufferedReader;
import java.io.IOException;

public class EchoCommand implements Command{

    private final String ECHO_COMMAND = "ECHO";

    @Override
    public String execute(String input, BufferedReader br) {

        try {
            String echo = br.readLine();
            echo = br.readLine();
            return String.format("$%d\r\n%s\r\n",echo.length(), echo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean mathces(String input) {
        return input.equalsIgnoreCase(ECHO_COMMAND);
    }
}
