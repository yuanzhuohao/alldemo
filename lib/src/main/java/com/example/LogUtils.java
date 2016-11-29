package com.example;

/**
 * Created by JessYuan on 24/11/2016.
 */

public class LogUtils {
    public static void log(Object msg) {
        System.out.println(
                Thread.currentThread().getName() + ": " + msg
        );
    }
}
