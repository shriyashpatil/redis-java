package dto;

import java.util.concurrent.ConcurrentHashMap;
public class RedisCache {
    ConcurrentHashMap<String,String> hm = new ConcurrentHashMap<String,String>();

    private static RedisCache redisCache=null;
    private RedisCache(){}

    public static RedisCache getInstance(){
        if(redisCache==null) {
            synchronized (RedisCache.class) {
                if(redisCache==null)
                    redisCache = new RedisCache();
            }
        }
        return redisCache;
    }


    public void setValue(String key,String value){
        hm.put(key,value);
    }

    public void setValue(String key,String value,Integer expTime){

    }


    public String getValue(String key){
        return hm.get(key);
    }


}
