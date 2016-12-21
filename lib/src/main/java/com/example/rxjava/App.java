package com.example.rxjava;


import io.reactivex.Emitter;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jess Yuan on 28/10/2016.
 */

public class App {

    private static final String TAG = "RxJavaApp";

    public static void main(String[] args) {


//        Observable.create(new ObservableOnSubscribe<Integer>() {
//
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                System.out.println("Emitter one");
//                e.onNext(1);
//                System.out.println("Emitter two");
//                e.onNext(2);
//                System.out.println("Emitter three");
//                e.onNext(3);
//                System.out.println("Emitter four");
//                e.onNext(4);
//                System.out.println("Emitter five");
//                e.onNext(5);
//            }
//        }).subscribe(new Observer<Integer>() {
//            private Disposable mDisposable;
//
//            @Override
//            public void onSubscribe(Disposable d) {
//                mDisposable = d;
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                System.out.println("Output: " + value);
//                if (value == 2) {
//                    mDisposable.dispose();
//                    System.out.println("Dispose: " + mDisposable.isDisposed());
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        // Thread
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                System.out.println("Thread name: " + Thread.currentThread().getName());
                e.onNext(1);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("Thread name: " + Thread.currentThread().getName()
                    + " value: " + integer);
            }
        };

        observable.subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(consumer);
    }
}
