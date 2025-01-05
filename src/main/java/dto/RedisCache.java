package dto;

import java.util.concurrent.ConcurrentHashMap;
public class RedisCache {
    ConcurrentHashMap<String,String> cache = new ConcurrentHashMap<String,String>();
    ConcurrentHashMap<String,Long>  expiryCache = new ConcurrentHashMap<String,Long>();

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
        cache.put(key,value);
    }

    public void setValue(String key,String value,Integer ttl){
         cache.put(key,value);
         System.out.println("Setting time "+System.currentTimeMillis()+ttl);
         expiryCache.put(key,System.currentTimeMillis()+ttl*1000L);
    }


    public String getValue(String key){

        System.out.println("Getting time : "+System.currentTimeMillis());
        System.out.println("expiry time "+expiryCache.get(key));

        if(expiryCache.containsKey(key) && System.currentTimeMillis()>expiryCache.get(key)){
            cache.remove(key);
            expiryCache.remove(key);
            return "$-1\r\n";
        }
        String value = cache.get(key);
        return String.format("$%d\r\n%s\r\n",value.length(),value);
    }


}
