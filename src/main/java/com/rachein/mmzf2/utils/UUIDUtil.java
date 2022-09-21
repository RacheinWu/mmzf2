package com.rachein.mmzf2.utils;

import java.util.UUID;

public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    public static void main(String[] args) {
        for (int i = 0;; i++) {
            String uuid = uuid();
            System.out.println(uuid.substring(0,13).toUpperCase());
        }
    }

}
