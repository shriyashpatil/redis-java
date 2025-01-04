package commands;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {

    private final String DEFAULT = "+PONG\\r\\n";
    private final List<Command> commands = new ArrayList<>();

    public CommandExecutor() {

    }

    public void addCommand(Command command){commands.add(command);}

    public void removeCommand(Command command){commands.remove(command);}

    public String execute(String input, BufferedReader br){
        for(Command command : commands){
            if(command.mathces(input)){
               return  command.execute(input,br);
            }
        }
        return DEFAULT;
    }


}
