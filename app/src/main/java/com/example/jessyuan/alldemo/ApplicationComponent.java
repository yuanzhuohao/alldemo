package com.example.jessyuan.alldemo;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by JessYuan on 13/12/2016.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Context getContext();
}
