package com.xyz.common;

import java.util.UUID;

public class Utils {
    public static long createTimestamp() {
        return System.currentTimeMillis();
    }

    public static String createUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public void test(){
        System.out.println("测试git");
    }
}