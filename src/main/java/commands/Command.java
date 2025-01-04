package commands;

import java.io.BufferedReader;

public interface Command {

    String execute(String input, BufferedReader br);

    boolean mathces(String input);

}
