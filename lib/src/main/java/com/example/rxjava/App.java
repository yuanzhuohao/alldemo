package com.example.rxjava;

import java.math.BigInteger;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Jess Yuan on 28/10/2016.
 */

public class App {

        private static void log(Object msg) {
        System.out.println(
                Thread.currentThread().getName() + ": " + msg
        );
    }

    public static void main(String[] args) {
        // Observable create by range
//        log("Before");
//        Observable
//                .range(5, 3)
//                .subscribe(i -> {
//                    log(i);
//                });
//        log("After");

        // infinite Stream
        Observable<BigInteger> naturalNumbers = Observable.create(
                subscriber -> {
                    Runnable r = () -> {
                        BigInteger i = BigInteger.ZERO;
                        while (true) {
                            subscriber.onNext(i);
                            i = i.add(BigInteger.ONE);
                        }
                    };

                    new Thread(r).start();
                }
        );
    }
}
