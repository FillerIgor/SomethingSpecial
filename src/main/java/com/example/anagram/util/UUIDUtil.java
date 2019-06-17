package com.example.anagram.util;

import java.util.UUID;

public class UUIDUtil {

    private UUIDUtil(){
        throw new RuntimeException("Utility class. You can't create an instance");
    }

    public static UUID fromString(String uuid){
        return UUID.fromString(uuid);
    }

    public static String fromUUID(UUID uuid){
        return uuid.toString();
    }
}
