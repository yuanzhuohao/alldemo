package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by JessYuan on 15/11/2016.
 */

public class Main {
    private final static int NTHREADS = 10;

    public static void main(String[] args) {
        try {
            String content = new String(Files.readAllBytes(Paths.get("./lib/src/main/java/com/example/text.txt")));
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
