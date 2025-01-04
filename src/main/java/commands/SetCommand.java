package commands;

import dto.RedisCache;

import java.io.BufferedReader;
import java.io.IOException;

public class SetCommand implements Command{

    private final String SET_COMMAND = "SET";
    RedisCache cache = RedisCache.getInstance();;
    @Override
    public String execute(String input, BufferedReader br) {
            String key =getKey(br);
            String value = getValue(br);
            cache.setValue(key,value);
            return String.format("$%d\r\n%s\r\n","OK".length(), "OK");
    }

    private String getKey(BufferedReader br){
        try {
            br.readLine();
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkExpiry(BufferedReader br){

        return false;
    }

    private String getValue(BufferedReader br){
        try {
            br.readLine();
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean mathces(String input) {
        return input.equalsIgnoreCase(SET_COMMAND);
    }
}
