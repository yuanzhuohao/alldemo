package com.example.rxjava;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by JessYuan on 06/12/2016.
 */

public class RxBus {

    private static RxBus instance;

    private PublishSubject<Object> bus = PublishSubject.create();

    public static RxBus getInstance() {
        if (instance == null) {
            instance = new RxBus();
        }

        return instance;
    }

    public Observable getObservable() {
        return bus;
    }

    public void post(Object o) {
        bus.onNext(o);
    }

}
