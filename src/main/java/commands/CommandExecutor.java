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

    public String execute(String input, BufferedReader br){
        for(Command command : commands){
            System.out.println(command.getClass());
            if(command.mathces(input)){
               return  command.execute(input,br);
            }
        }
        return DEFAULT;
    }


}
