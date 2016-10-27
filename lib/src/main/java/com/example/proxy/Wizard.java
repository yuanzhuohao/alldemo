package com.example.proxy;

/**
 * Created by Jess Yuan on 27/10/2016.
 */

public class Wizard {
    private String name;

    public Wizard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Wizard name: " + name;
    }
}
