package com.example.mylibrary;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

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

    public <T> Disposable register(Consumer<T> consumer, final Class<T> clazz) {
        return getObservable().filter(new Predicate() {
            @Override
            public boolean test(Object o) throws Exception {
                return o.getClass() == clazz;
            }
        }).subscribe(consumer);
    }

    public void post(Object o) {
        bus.onNext(o);
    }

}
