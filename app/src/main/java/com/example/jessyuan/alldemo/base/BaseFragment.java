package com.example.jessyuan.alldemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;

import com.example.jessyuan.alldemo.interfaces.IFragmentKeyDown;

import butterknife.ButterKnife;

/**
 * Created by Jess Yuan on 17/10/2016.
 */

public class BaseFragment extends Fragment implements IFragmentKeyDown {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onKeyDown(int keyCode, KeyEvent event) {
    }
}
