package com.example.jessyuan.alldemo;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jessyuan.alldemo.fragment.MainFragment;
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

    private long lastTimeStamp = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (getSupportFragmentManager().findFragmentById(android.R.id.content) instanceof MainFragment) {
                if ((lastTimeStamp != 0) && (System.currentTimeMillis() - lastTimeStamp < 2000)) {
                    finish();
                } else {
                    lastTimeStamp = System.currentTimeMillis();
                    ToastUtils.makeTextShort(this, "再点击次退出");
                }
            } else {
                super.onKeyDown(keyCode, event);
            }
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
