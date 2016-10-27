package com.example.jessyuan.alldemo.module;

import android.content.Context;

import org.eclipse.equinox.app.IApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jess Yuan on 20/10/2016.
 */

@Module
public class AppModule {

    Context mContext;

    AppModule(Context context) {
        mContext = context;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }

}
