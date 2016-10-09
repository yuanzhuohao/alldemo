package com.example.mylibrary;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Jess Yuan on 26/09/2016.
 */

public class RxBus {

    private static RxBus instance;

    private Subject<Object, Object> bus = new SerializedSubject<>(PublishSubject.create());

    public static RxBus getInstance() {
        if (instance == null) {
            instance = new RxBus();
        }

        return instance;
    }

    public Observable getObservable() {
        return bus;
    }

    public boolean hasObserver() {
        if (bus.hasObservers()) {
            return true;
        } else {
            return false;
        }
    }

}
