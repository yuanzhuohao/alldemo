package com.example.single;

/**
 * Created by Jess Yuan on 27/10/2016.
 */

public class App {
    public static void main(String[] args) {
        Simple simple1 = Simple.getInstance();
        Simple simple2 = Simple.getInstance();
        simple1.setNumber(10);
        System.out.println("simple1: " + simple1 + " " + simple1.getNumber());
        System.out.println("simple2: " + simple2 + " " + simple2.getNumber());

        InitializeLazySingleton initializeLazySingleton1 = InitializeLazySingleton.getInstance();
        InitializeLazySingleton initializeLazySingleton2 = InitializeLazySingleton.getInstance();
        System.out.println("initializeLazySingleton1: " + initializeLazySingleton1);
        System.out.println("initializeLazySingleton2: " + initializeLazySingleton2);

        InitializingOnDemandHolder initializingOnDemandHolder1 = InitializingOnDemandHolder.getInstance();
        InitializingOnDemandHolder initializingOnDemandHolder2 = InitializingOnDemandHolder.getInstance();
        System.out.println("initializingOnDemandHolder1: " + initializingOnDemandHolder1);
        System.out.println("initializingOnDemandHolder2: " + initializingOnDemandHolder2);

        ThreadSafeDoubleCheckLock threadSafeDoubleCheckLock1 = ThreadSafeDoubleCheckLock.getInstance();
        ThreadSafeDoubleCheckLock threadSafeDoubleCheckLock2 = ThreadSafeDoubleCheckLock.getInstance();
        System.out.println("threadSafeDoubleCheckLock1: " + threadSafeDoubleCheckLock1);
        System.out.println("threadSafeDoubleCheckLock2: " + threadSafeDoubleCheckLock2);
    }
}
