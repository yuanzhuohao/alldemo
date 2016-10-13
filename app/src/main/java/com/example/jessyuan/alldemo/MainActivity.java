package com.example.jessyuan.alldemo;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jessyuan.alldemo.fragment.MainFragment;
import com.example.jessyuan.alldemo.service.GenerateNumberService;
import com.example.mylibrary.FragmentUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentUtils.replaceFragment(getSupportFragmentManager()
                                    , new MainFragment()
                                    , android.R.id.content);

    }

}
