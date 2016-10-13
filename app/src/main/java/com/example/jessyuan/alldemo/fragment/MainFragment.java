package com.example.jessyuan.alldemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.jessyuan.alldemo.MainActivity;
import com.example.jessyuan.alldemo.R;
import com.example.mylibrary.FragmentUtils;
import com.example.mylibrary.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Jess Yuan on 23/09/2016.
 */

public class MainFragment extends BaseFragment {

    @BindView(R.id.toobar)
    Toolbar toolbar;

    @Override
    public int setLayoutViewId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        toolbar.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        toolbar.setTitle("All Demo For This");
        toolbar.setNavigationIcon(android.R.drawable.arrow_down_float);
        toolbar.inflateMenu(R.menu.toolbar_menu);
    }

    @OnClick(R.id.btn_load_more)
    void loadMore() {
        LogUtils.d("test", "Hello");
        FragmentUtils.replaceFragment(mFragmentManager
                                    , new LoadMoreFragment()
                                    , android.R.id.content);
    }

    @OnClick(R.id.btn_toolbar_show_hide)
    void toolbarShowHide() {
        FragmentUtils.replaceFragment(mFragmentManager
                                    , new ToolbarShowHideFragment()
                                    , android.R.id.content);
    }

    @OnClick(R.id.btn_ai_demo)
    void aidemo() {
        FragmentUtils.replaceFragment(mFragmentManager
                                    , new AIFragment()
                                    , android.R.id.content);
    }

    @OnClick(R.id.btn_zxing_demo)
    void zxingdemo() {
        FragmentUtils.replaceFragment(mFragmentManager
                                    , new ZXingFragment()
                                    , android.R.id.content);
    }

    @OnClick(R.id.btn_scroll_demo)
    void scrolldemo() {
        FragmentUtils.replaceFragment(mFragmentManager
                                    , new ScrollDemoFragment()
                                    , android.R.id.content);
    }

    @OnClick(R.id.btn_service_demo)
    void serviceDemo() {
        FragmentUtils.replaceFragment(mFragmentManager,
                                    new ServiceDemoFragment(),
                                    android.R.id.content);
    }

}
