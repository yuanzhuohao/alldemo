package com.example.jessyuan.alldemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.jessyuan.alldemo.R;

import butterknife.ButterKnife;

/**
 * Created by Jess Yuan on 17/10/2016.
 *
 * 继承该类的子类的布局必须导入Toolbar的布局或Toolbar的id为toolbar布局
 */

public class BaseToolbarFragment extends BaseFragment {

    private ActionBar mToolbar;
    private FrameLayout mContentLayout;
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_base_toolbar, container, false);
        Toolbar toolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
        mContentLayout = (FrameLayout) mRootView.findViewById(R.id.fl_content);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        mToolbar =  ((AppCompatActivity)getActivity()).getSupportActionBar();

        if (mToolbar != null) {
            mToolbar.setDisplayShowTitleEnabled(true);
        }

        return mRootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected ActionBar getToolbar() {
        return mToolbar;
    }


    /**
     * Set content view layout in container, it will be called after fragment's layout view has render, so it was called on
     * 'onViewCreated' method.
     * @param view
     */
    public void setContentView(View view) {
        mContentLayout.addView(view);
        ButterKnife.bind(this, view);
    }

    /**
     * Set content view layout in container, it will be called after fragment's layout view has render, so it was called on
     * 'onViewCreated' method.
     * @param layoutResID
     */
    public void setContentView(int layoutResID) {
        View view = getActivity().getLayoutInflater().inflate(layoutResID, mContentLayout);
        ButterKnife.bind(this, view);
    }

    /**
     * Set content view layout in container, it will be called after fragment's layout view has render, so it was called on
     * 'onViewCreated' method.
     * @param view
     * @param params
     */
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContentLayout.addView(view, params);
        ButterKnife.bind(this, view);
    }

}
