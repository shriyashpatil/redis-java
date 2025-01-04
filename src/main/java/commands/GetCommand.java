package commands;

import dto.RedisCache;

import java.io.BufferedReader;
import java.io.IOException;

public class GetCommand implements Command{

    private final String GET_COMMAND = "GET_COMMAND";

    RedisCache cache = RedisCache.getInstance();

    @Override
    public String execute(String input, BufferedReader br) {
        String key = getKey(br);
        String value = cache.getValue(key);
        return String.format("$%d\r\n%s\r\n",value.length(),value);
    }

    private String getKey(BufferedReader br){
        try {
            br.readLine();
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean mathces(String input) {
        return input.equalsIgnoreCase(GET_COMMAND);
    }
}
