package com.example.jessyuan.alldemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.example.jessyuan.alldemo.fragment.LoginFragment;
import com.example.jessyuan.alldemo.fragment.MainFragment;
import com.example.jessyuan.alldemo.helper.UserManager;
import com.example.jessyuan.alldemo.interfaces.IFragmentKeyDown;
import com.example.mylibrary.FragmentUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentUtils.addFragmentToActivity(getSupportFragmentManager()
                                    , new MainFragment()
                                    , android.R.id.content);
    }

    /**
     * add listener for FirebaseAuth
     */
    @Override
    protected void onStart() {
        super.onStart();
        UserManager.getInstance().addAuthStateListener();
    }

    /**
     * remove listener when activity was stop
     */
    @Override
    protected void onStop() {
        super.onStop();
        UserManager.getInstance().removeAuthStateListener();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(android.R.id.content);
        if (fragment != null && fragment instanceof IFragmentKeyDown
                && (fragment instanceof MainFragment || fragment instanceof LoginFragment)) {
            ((IFragmentKeyDown)fragment).onKeyDown(keyCode, event);
        } else {
            super.onKeyDown(keyCode, event);
        }

        return true;
    }

}
