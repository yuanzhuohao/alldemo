package com.example.jessyuan.alldemo.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/** Provide Application
 * Created by JessYuan on 13/12/2016.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providerApplication() {
        return mApplication;
    }

}
