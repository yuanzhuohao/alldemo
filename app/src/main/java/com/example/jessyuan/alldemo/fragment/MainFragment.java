package com.example.jessyuan.alldemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.base.BaseToolbarFragment;
import com.example.mylibrary.FragmentUtils;
import com.example.mylibrary.RxBus;
import com.example.mylibrary.ToastUtils;

import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Jess Yuan on 17/10/2016.
 */

public class MainFragment extends BaseToolbarFragment {

    private static final String TAG = "MainFragment";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setContentView(R.layout.fragment_main);
    }

    @Override
    public void setToolbar(ActionBar toolbar) {
        toolbar.setTitle("All Demo");
    }

    @OnClick(R.id.btn_album)
    void pickpicture() {
        FragmentUtils.replaceFragment(getFragmentManager(),
                new AlbumFragment(),
                android.R.id.content);
    }

    @OnClick(R.id.btn_custom_view)
    void customView() {
        FragmentUtils.replaceFragment(getFragmentManager(),
                new CustomViewFragment(),
                android.R.id.content);
    }

    private long lastTimeStamp = 0;

    @Override
    public void onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((lastTimeStamp != 0) && (System.currentTimeMillis() - lastTimeStamp < 2000)) {
                getActivity().finish();
            } else {
                lastTimeStamp = System.currentTimeMillis();
                ToastUtils.makeTextShort(getActivity(), "再点击次退出");

            }
        } else {
            super.onKeyDown(keyCode, event);
        }
    }
}
