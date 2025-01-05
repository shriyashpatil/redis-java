package commands;

import dto.RedisCache;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class GetCommand implements Command{

    private final String GET_COMMAND = "GET";

    RedisCache cache = RedisCache.getInstance();

    @Override
    public String execute(List<String> tokens) {
        String key = tokens.get(1);
        return cache.getValue(key);
    }

    @Override
    public boolean mathces(String input) {
        return input.equalsIgnoreCase(GET_COMMAND);
    }
}
