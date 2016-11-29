package com.example.rxjava;

import com.example.LogUtils;

import java.math.BigInteger;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.observables.BlockingObservable;
import rx.subjects.PublishSubject;

/**
 * Created by Jess Yuan on 28/10/2016.
 */

public class App {

    public static void main(String[] args) {
        System.out.println(
                String.format("%.3f", 123.123124123123)
        );
    }
}
