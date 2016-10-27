package com.example.mylibrary;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
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

    public Subscription register(final Observer<Object> observer, final Class clazz) {
        return bus.asObservable()
                .filter(new Func1<Object, Boolean>() {
                    @Override
                    public Boolean call(Object o) {
                        if (clazz.isInstance(o)) {
                            return true;
                        }

                        return false;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public Subscription register(final Action1 action1, final Class clazz) {
        return bus.asObservable()
                .filter(new Func1<Object, Boolean>() {
                    @Override
                    public Boolean call(Object o) {
                        if (clazz.isInstance(o)) {
                            return true;
                        }

                        return false;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    public void register(Observer<Object> observer) {
        register(observer, Object.class);
    }

    public void register(Action1 action1) {
        register(action1, Object.class);
    }

    public void send(Object o) {
        bus.onNext(o);
    }

    public Observable<Object> getObservable() {
        return bus.asObservable();
    }

    public boolean hasObservers() {
        if (bus.hasObservers()) {
            return true;
        } else {
            return false;
        }
    }

    public interface Action {
        void func(Object o);
    }
}
