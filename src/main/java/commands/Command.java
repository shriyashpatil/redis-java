package commands;

import java.io.BufferedReader;
import java.util.List;

public interface Command {

    String execute(List<String> tokens);

    boolean mathces(String input);

}
