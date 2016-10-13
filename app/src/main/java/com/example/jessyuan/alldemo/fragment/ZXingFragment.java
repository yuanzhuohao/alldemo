package com.example.jessyuan.alldemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.example.jessyuan.alldemo.R;
import com.google.zxing.integration.android.IntentIntegrator;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Jess Yuan on 09/10/2016.
 */

public class ZXingFragment extends BaseFragment {

    @BindView(R.id.btn_zxing_scan)
    Button scanButton;

    @Override
    public int setLayoutViewId() {
        return R.layout.fragment_zxing_demo;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick(R.id.btn_zxing_scan)
    void scan() {
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.initiateScan(0);
    }
}
