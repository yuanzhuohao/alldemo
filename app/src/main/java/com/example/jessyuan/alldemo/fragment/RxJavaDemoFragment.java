package com.example.jessyuan.alldemo.fragment;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jessyuan.alldemo.R;
import com.example.mylibrary.LogUtils;
import com.example.mylibrary.RxBus;
import com.example.mylibrary.ToastUtils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.AsyncOnSubscribe;
import rx.observables.ConnectableObservable;
import rx.subjects.PublishSubject;

/**
 * Created by Jess Yuan on 18/10/2016.
 */

public class RxJavaDemoFragment extends BaseNaviFragment {

    RxBus mRxBus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rxjava_demo, container, false);
    }

    @Override
    void setToolbar() {
        getToolbar().setTitle("RxJava Demo");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        demo();
        rxbusdemo();
    }

    private void rxbusdemo() {
        mRxBus = RxBus.getInstance();
        Observer<Object> observer1 = new Observer<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object event) {
                LogUtils.i("observer1", ((Event)event).getName());
            }
        };

        Observer<Object> observer2 = new Observer<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object event) {
                LogUtils.i("observer2", "Everything" + (Looper.myLooper() == Looper.getMainLooper()));
            }
        };

        mRxBus.register(observer2);
        mRxBus.register(observer1, Event.class);
        mRxBus.register(new Action1() {
            @Override
            public void call(Object o) {
                LogUtils.i("Action1", ((Event)o).getName());
            }
        }, Event.class);

        mRxBus.send("string");
    }

    public class Event {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Event(String name) {
            this.name = name;
        }

        String name;

    }

    private void demo() {
        PublishSubject<String> subject = PublishSubject.create();
        Observer<String> observer1 = new Observer<String>() {
            @Override
            public void onCompleted() {
                LogUtils.i("observer1", "complete");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                LogUtils.i("observer1", s);
            }
        };

        Observer<String> observer2 = new Observer<String>() {
            @Override
            public void onCompleted() {
                LogUtils.d("observer2","complete");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                LogUtils.i("observer2", s);
            }
        };

        subject.subscribe(observer1);
        subject.onNext("one");
        subject.onNext("two");
        subject.subscribe(observer2);
        subject.onNext("three");
        subject.onCompleted();

    }

}
