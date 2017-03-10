package com.example.single;

/**
 * Created by Jess Yuan on 27/10/2016.
 */

public class App {
    public static void main(String[] args) {
        BillPughSingleton singleton = BillPughSingleton.getInstance();
        singleton.setCount(2);
        BillPughSingleton singleton1 = BillPughSingleton.getInstance();
        System.out.println(singleton1.getCount());
    }
}
