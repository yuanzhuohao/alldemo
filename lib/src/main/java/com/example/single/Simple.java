package com.example.single;

/**
 * Created by Jess Yuan on 27/10/2016.
 */

public class Simple {

    public Simple() {
    }

    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private static final Simple INSTANCE = new Simple();

    public static Simple getInstance() {
        return INSTANCE;
    }
}
