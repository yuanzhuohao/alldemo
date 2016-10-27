package com.example.jessyuan.alldemo.fragment;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.jessyuan.alldemo.R;
import com.example.mylibrary.LogUtils;
import com.example.mylibrary.RxBus;
import com.example.mylibrary.ToastUtils;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * Created by Jess Yuan on 26/10/2016.
 */

public class BottomNaviFragment extends BaseNaviFragment {

    @BindView(R.id.bottom_navigator_view)
    BottomNavigationView mBottomNavigationView;

    RxBus mRxBus;

    @Override
    void setToolbar() {
        getToolbar().setTitle("Bottom Navigator Demo");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           return inflater.inflate(R.layout.fragment_bottom_navigator, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxBus = RxBus.getInstance();

        mRxBus.register(new Action1() {
            @Override
            public void call(Object o) {
                LogUtils.i("Bottom", "change" +
                (Looper.myLooper() == Looper.getMainLooper()));

                mBottomNavigationView.getMenu()
                        .findItem(R.id.action_one)
                        .setIcon(R.drawable.ic_money_off_black_18dp);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

//        mRxBus.send("string");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });

    }

    public class Event {

    }
}
