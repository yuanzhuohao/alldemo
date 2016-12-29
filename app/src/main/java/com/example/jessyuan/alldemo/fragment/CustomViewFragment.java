package com.example.jessyuan.alldemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by JessYuan on 30/11/2016.
 */

public class CustomViewFragment extends BaseFragment {

    private static final String TAG = "CustomViewFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
