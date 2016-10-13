package com.example.jessyuan.alldemo.fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.ui.MyView;
import com.example.mylibrary.LogUtils;

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
        ValueAnimator animator = ValueAnimator.ofFloat(0, 100);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animation.getAnimatedFraction();
                LogUtils.i("AnimatedFraction", animation.getAnimatedFraction() +
                        " " +
                        animation.getAnimatedValue());
            }
        });

        animator.setDuration(3000);
        animator.start();
    }
}
