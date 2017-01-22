package com.example.jessyuan.alldemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.example.jessyuan.alldemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JessYuan on 19/01/2017.
 */

public class BaseToolbarActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_toolbar);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

}
