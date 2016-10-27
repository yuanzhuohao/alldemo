package com.example.single;

/**
 * Created by Jess Yuan on 27/10/2016.
 */

public class ThreadSafeDoubleCheckLock {

    private static ThreadSafeDoubleCheckLock instance;

    public ThreadSafeDoubleCheckLock() {
        if (instance != null) {
            throw new IllegalStateException("Already Initialize");
        }
    }

    public static ThreadSafeDoubleCheckLock getInstance() {
        ThreadSafeDoubleCheckLock result = instance;

        if (result == null) {

            synchronized (ThreadSafeDoubleCheckLock.class) {
                result = instance;

                if (result == null) {
                    result = instance = new ThreadSafeDoubleCheckLock();
                }
            }

        }

        return result;
    }


}
