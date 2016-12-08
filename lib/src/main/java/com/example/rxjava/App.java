package com.example.rxjava;


import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * Created by Jess Yuan on 28/10/2016.
 */

public class App {

    public static void main(String[] args) {
        RxBus rxBus = RxBus.getInstance();

        rxBus.getObservable().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });

        rxBus.post("hello");
        rxBus.post("yes");
    }
}
