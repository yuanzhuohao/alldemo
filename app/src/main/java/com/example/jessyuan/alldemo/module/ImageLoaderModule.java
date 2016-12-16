package com.example.jessyuan.alldemo.module;

import android.content.Context;

import com.example.jessyuan.alldemo.helper.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JessYuan on 14/12/2016.
 */

@Module(includes = ApplicationModule.class)
public class ImageLoaderModule {

    @Provides
    @Singleton
    ImageLoader providerImageLoader(Context context) {
        return new ImageLoader(context);
    }

}
