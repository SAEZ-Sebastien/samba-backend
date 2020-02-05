package fr.miage.samba.backend.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

public abstract class CacheHelper{

    private static HashMap<String,String> usersCache = new HashMap<>();

    public static void addToCache(String key, String value){
        usersCache.put(key,value);
    }

   public static void removeFromCache(String key){
        usersCache.remove(key);
    }

    public static String getValue(String key){
        return usersCache.get(key);
    }

}
