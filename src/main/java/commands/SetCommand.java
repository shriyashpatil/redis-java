package commands;

import dto.RedisCache;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class SetCommand implements Command{

    private final String SET_COMMAND = "SET";
    RedisCache cache = RedisCache.getInstance();;
    @Override
    public String execute(List<String> tokens) {
            String key = tokens.get(1);
            String value = tokens.get(2);
            if(checkExpiry(tokens)){
                System.out.println("ttl set----------");
                cache.setValue(key,value,getTtl(tokens.get(4)));
            }else {
                cache.setValue(key, value);
            }
            return String.format("$%d\r\n%s\r\n","OK".length(), "OK");
    }

    
    private boolean checkExpiry(List<String> tokens){
            if(tokens.size()<=3) return false;
            String px = tokens.get(3);
            return px.equalsIgnoreCase("PX");
    }

    private  Integer getTtl(String ttl){
            return Integer.valueOf(ttl);
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
