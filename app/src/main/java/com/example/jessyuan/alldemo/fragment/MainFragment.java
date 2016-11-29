package com.example.jessyuan.alldemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.base.BaseToolbarFragment;
import com.example.mylibrary.FragmentUtils;

import butterknife.OnClick;

/**
 * Created by Jess Yuan on 17/10/2016.
 */

public class MainFragment extends BaseToolbarFragment {

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

}
