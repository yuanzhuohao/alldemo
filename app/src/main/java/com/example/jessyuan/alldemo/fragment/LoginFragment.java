package com.example.jessyuan.alldemo.fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.EditText;

import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.base.BaseToolbarFragment;
import com.example.jessyuan.alldemo.helper.UserManager;
import com.example.mylibrary.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JessYuan on 08/12/2016.
 */

public class LoginFragment extends BaseToolbarFragment {

    private static final String TAG = "LoginFragment";

    @BindView(R.id.et_login_email)
    EditText emailEditText;
    @BindView(R.id.et_login_password)
    EditText passwordEditText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setContentView(R.layout.fragment_login);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @OnClick(R.id.btn_login_login)
    void login() {
        String email = emailEditText.getText().toString();
        String pwd = passwordEditText.getText().toString();

        UserManager.getInstance().getAuth().signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isComplete()) {
                            ToastUtils.makeTextShort(getActivity(), "Login failed, Try again!");
                        } else {
                            ToastUtils.makeTextShort(getActivity(), "Login in!");
                        }
                    }
                });
    }

    @OnClick(R.id.btn_login_register)
    void register() {

    }
}
