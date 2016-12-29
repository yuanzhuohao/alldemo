package com.example.jessyuan.alldemo;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by JessYuan on 14/11/2016.
 */

public class AllDemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
    }

}
