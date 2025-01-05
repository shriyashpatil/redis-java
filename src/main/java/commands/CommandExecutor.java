package commands;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {

    private final String DEFAULT = "+PONG\r\n";
    private List<Command> commands = new ArrayList<>();

    public CommandExecutor() {
        addCommand(new PingCommand());
        addCommand(new EchoCommand());
        addCommand(new SetCommand());
        addCommand(new GetCommand());
    }

    public void addCommand(Command command){commands.add(command);}

    public void removeCommand(Command command){commands.remove(command);}

    public String execute(List<String> tokens){
        for(Command command : commands){
            System.out.println(command.getClass());
            if(command.mathces(tokens.get(0))){
               return  command.execute(tokens);
            }
        }
        return DEFAULT;
    }


}
