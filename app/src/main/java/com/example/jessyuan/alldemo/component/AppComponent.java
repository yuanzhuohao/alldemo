package com.example.jessyuan.alldemo.component;

import android.support.v4.app.Fragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Jess Yuan on 20/10/2016.
 */

@Singleton
@Component
public interface AppComponent {
    void inject(Fragment fragment);
}
