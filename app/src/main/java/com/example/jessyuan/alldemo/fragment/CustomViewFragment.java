package com.example.jessyuan.alldemo.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.base.BaseNaviFragment;
import com.example.mylibrary.LogUtils;
import com.example.mylibrary.RxBus;
import com.example.mylibrary.ToastUtils;

import java.util.concurrent.Callable;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by JessYuan on 30/11/2016.
 */

public class CustomViewFragment extends BaseNaviFragment {

    private static final String TAG = "CustomViewFragment";

    CompositeDisposable disposables = new CompositeDisposable();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setContentView(R.layout.fragment_custom_view);
        getToolbar().setTitle("CustomView");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }

    private Observable<String> getObservable() {
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                SystemClock.sleep(3000);
                return Observable.just("one", "two", "three", "four", "five");
            }
        });
    }

    @OnClick(R.id.btn_button1)
    void button1() {
        disposables.add(getObservable()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableObserver<String>() {
                                @Override
                                public void onNext(String value) {
                                    ToastUtils.makeTextShort(getActivity(), value);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    LogUtils.e(TAG, e.getMessage());
                                }

                                @Override
                                public void onComplete() {
                                    ToastUtils.makeTextShort(getActivity(), "complete");
                                }
                            }));

        LogUtils.i(TAG, "disposables count: " + disposables.size());
    }
}
