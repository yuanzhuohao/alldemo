package com.example.jessyuan.alldemo;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jessyuan.alldemo.fragment.MainFragment;
import com.example.jessyuan.alldemo.interfaces.IFragmentKeyDown;
import com.example.mylibrary.FragmentUtils;
import com.example.mylibrary.ToastUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentUtils.addFragmentToActivity(getSupportFragmentManager()
                                    , new MainFragment()
                                    , android.R.id.content);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(android.R.id.content);
        if (fragment != null && fragment instanceof IFragmentKeyDown
                && fragment instanceof MainFragment) {
            ((IFragmentKeyDown)fragment).onKeyDown(keyCode, event);
        } else {
            super.onKeyDown(keyCode, event);
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(android.R.id.content);
        if (fragment != null) {
            fragment.onCreateOptionsMenu(menu, getMenuInflater());
            return true;
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(android.R.id.content);
        if (fragment != null) {
            fragment.onOptionsItemSelected(item);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
