package com.example.single;

/**
 * Created by Jess Yuan on 27/10/2016.
 */

public class InitializeLazySingleton {
    private static InitializeLazySingleton instance;

    private int number;

    public InitializeLazySingleton() {}

    public static InitializeLazySingleton getInstance() {
        if (instance == null) {
            instance = new InitializeLazySingleton();
        }

        return instance;
    }
}
