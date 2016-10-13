package com.example.jessyuan.alldemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.mylibrary.LogUtils;

import java.util.Random;

/**
 * Created by Jess Yuan on 13/10/2016.
 */

public class GenerateNumberService extends Service {

    private final static String TAG = "GenerateNumberService";

    private Random mRandom = new Random();

    private  IBinder mBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class MyBinder extends Binder {
        public GenerateNumberService getService() {
            return GenerateNumberService.this;
        }
    }

    public int getRandomNumber() {
        return mRandom.nextInt();
    }

}
