package com.example.jessyuan.alldemo.component;

import com.example.jessyuan.alldemo.fragment.DaggerDemoFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Jess Yuan on 20/10/2016.
 */

@Singleton
public interface AppComponent {
    void inject(DaggerDemoFragment fragment);
}
