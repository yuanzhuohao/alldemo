package com.example.jessyuan.alldemo;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JessYuan on 13/12/2016.
 */

@Module
public class ApplicationModule {

    private final Context mContext;

    ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }

}
