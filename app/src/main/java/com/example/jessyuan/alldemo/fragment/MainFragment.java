package com.example.jessyuan.alldemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.base.BaseToolbarFragment;
import com.example.mylibrary.FragmentUtils;

import butterknife.OnClick;

/**
 * Created by Jess Yuan on 17/10/2016.
 */

public class MainFragment extends BaseToolbarFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setToolbar(Toolbar toolbar) {
        toolbar.setTitle("All Demo");
    }

    @OnClick(R.id.btn_rxjava_demo)
    void rxjavademo() {
        FragmentUtils.replaceFragment(getFragmentManager(),
                new RxJavaDemoFragment(),
                android.R.id.content);
    }

    @OnClick(R.id.btn_pick_pictures)
    void pickpicture() {
        FragmentUtils.replaceFragment(getFragmentManager(),
                new AlbumFragment(),
                android.R.id.content);
    }

    @OnClick(R.id.btn_dagger_demo)
    void daggerdemo() {
        FragmentUtils.replaceFragment(getFragmentManager(),
                new DaggerDemoFragment(),
                android.R.id.content);
    }

    @OnClick(R.id.btn_qrcode_scan_demo)
    void qrcodescandemo() {
        FragmentUtils.replaceFragment(getFragmentManager(),
                new QRCodeScanFragment(),
                android.R.id.content);
    }

    @OnClick(R.id.btn_popupwindow_demo)
    void popupwindowdemo() {
        FragmentUtils.replaceFragment(getFragmentManager(),
                new PopupWindowDemoFragment(),
                android.R.id.content);
    }

}
