package com.example.jessyuan.alldemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.mylibrary.LogUtils;

/**
 * Created by Jess Yuan on 13/10/2016.
 */

public class DownloadSomethingService extends Service {

    private final static String TAG = DownloadSomethingService.class.getName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.i(TAG, "下载东西仲...");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LogUtils.i(TAG, "下载完成");
                stopSelf();
            }
        },3000);
        return START_STICKY;
    }
}
