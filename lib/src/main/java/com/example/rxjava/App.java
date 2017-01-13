package com.example.rxjava;


import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.Emitter;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Jess Yuan on 28/10/2016.
 */

public class App {

    private static final String TAG = "RxJavaApp";

    public static void main(String[] args) {

        ResourceSubscriber<Integer> subscriber = new ResourceSubscriber<Integer>() {
            @Override
            protected void onStart() {
                request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                System.out.println("Done");
            }
        };

        Flowable.range(1, 10).delay(1, TimeUnit.SECONDS).subscribe(subscriber);
        subscriber.dispose();

    }
}
