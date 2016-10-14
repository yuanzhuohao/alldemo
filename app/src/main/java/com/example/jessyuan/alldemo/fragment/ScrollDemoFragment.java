package com.example.jessyuan.alldemo.fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.os.EnvironmentCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.ui.MyView;
import com.example.mylibrary.LogUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;

/**
 * Created by Jess Yuan on 09/10/2016.
 */

public class ScrollDemoFragment extends BaseFragment {

    @BindView(R.id.myview)
    MyView mView;

    @Override
    public int setLayoutViewId() {
        return R.layout.fragment_scroll_demo;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        ValueAnimator animator = ValueAnimator.ofFloat(0, 100);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                animation.getAnimatedFraction();
//                LogUtils.i("AnimatedFraction", animation.getAnimatedFraction() +
//                        " " +
//                        animation.getAnimatedValue());
//            }
//        });
//
//        animator.setDuration(3000);
//        animator.start();

        String filename = "hello_world.txt";
        String string = "hello world!";

        String dir = getContext().getExternalFilesDir(null).toString();
        LogUtils.i("外部文件夹路径", dir);

    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        } else {
            return false;
        }
    }
}
