package com.example.jessyuan.alldemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.base.BaseNaviFragment;
import com.example.mylibrary.ToastUtils;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.OnClick;

/**
 * Created by Jess Yuan on 20/10/2016.
 */

public class QRCodeScanFragment extends BaseNaviFragment {

    private String toast;

    @Override
    public void setToolbar(Toolbar toolbar) {
        toolbar.setTitle("QRCode Scan Demo");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_qrcode_scan, container, false);
    }

    @OnClick(R.id.btn_scan)
    void scan() {
        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this);
        integrator.setBeepEnabled(false);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                toast = "Cancelled";
            } else {
                toast = result.getContents();
            }
        }

        displayToast();
    }

    private void displayToast() {
        if (toast != null) {
            ToastUtils.makeTextShort(getActivity(), toast);
        }
    }


}
