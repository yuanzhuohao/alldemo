package com.example.jessyuan.alldemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.album.AlbumFragment;
import com.example.jessyuan.alldemo.baidumap.BaiduMapFragment;
import com.example.jessyuan.alldemo.base.BaseToolbarFragment;
import com.example.jessyuan.alldemo.githubapi.GithubFragment;
import com.example.jessyuan.alldemo.weather.WeatherIndexFragment;
import com.example.mylibrary.FragmentUtils;
import com.example.mylibrary.ToastUtils;

import butterknife.OnClick;

/**
 * Created by Jess Yuan on 17/10/2016.
 */

public class MainFragment extends BaseToolbarFragment {

    private static final String TAG = "MainFragment";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setContentView(R.layout.fragment_main);
        getToolbar().setTitle("All Demo");
    }

    @OnClick(R.id.btn_album)
    public void album(View view) {
        FragmentUtils.replaceFragment(getFragmentManager(),
                new AlbumFragment(),
                android.R.id.content);
    }

    @OnClick(R.id.btn_custom_view)
    public void customview(View view) {
        FragmentUtils.replaceFragment(getFragmentManager(),
                new CustomViewFragment(),
                android.R.id.content);
    }

    @OnClick(R.id.btn_github_api)
    public void githubapi(View view) {
        FragmentUtils.replaceFragment(getFragmentManager(),
                new GithubFragment(),
                android.R.id.content);
    }

    @OnClick(R.id.btn_baidumap)
    public void baidumap(View view) {
        FragmentUtils.replaceFragment(getFragmentManager(),
                new BaiduMapFragment(),
                android.R.id.content);
    }

    @OnClick(R.id.btn_weather)
    public void weather(View view) {
        FragmentUtils.replaceFragment(getFragmentManager(),
                new WeatherIndexFragment(),
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
