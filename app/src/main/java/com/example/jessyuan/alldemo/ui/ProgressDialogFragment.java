package com.example.jessyuan.alldemo.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.example.jessyuan.alldemo.R;

/**
 * Created by JessYuan on 29/12/2016.
 */

public class ProgressDialogFragment extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getActivity(), getTheme());
        dialog.setTitle("Please wait...");
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(true);
        dialog.setProgressStyle(R.style.ProgressBar);
        return dialog;
    }
}
