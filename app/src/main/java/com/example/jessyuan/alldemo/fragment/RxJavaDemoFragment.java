package com.example.jessyuan.alldemo.fragment;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.base.BaseNaviFragment;
import com.example.mylibrary.LogUtils;
import com.example.mylibrary.RxBus;
import com.example.mylibrary.ToastUtils;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
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
    public void setToolbar(Toolbar toolbar) {
        toolbar.setTitle("RxJava Demo");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Observable.just("hello", "world").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                ToastUtils.makeTextShort(getContext(), s);
            }
        });
    }


}
