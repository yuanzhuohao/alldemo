package com.example.jessyuan.alldemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.jessyuan.alldemo.R;

/**
 * Created by Jess Yuan on 17/10/2016.
 *
 * 继承该类的子类的布局必须导入Toolbar的布局或Toolbar的id为toolbar布局
 */

public abstract class BaseToolbarFragment extends BaseFragment {

    Toolbar mToolbar;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        setToolbar();
    }

    protected Toolbar getToolbar() {
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        return mToolbar;
    }


    /**
     * 设置Toolbar, 布局要有toolbar
     */
    abstract void setToolbar();

}
