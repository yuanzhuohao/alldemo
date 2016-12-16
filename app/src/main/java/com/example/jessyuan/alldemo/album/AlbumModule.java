package com.example.jessyuan.alldemo.album;

import android.content.Context;

import com.example.jessyuan.alldemo.camera.DefaultCameraModule;
import com.example.jessyuan.alldemo.helper.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JessYuan on 13/12/2016.
 */

@Module
public class AlbumModule {

    private AlbumContract.AlbumView mView;
    private Context mContext;

    public AlbumModule(Context context, AlbumContract.AlbumView view) {
        mView = view;
        mContext = context;
    }

    @Provides
    @Singleton
    Context providerContext() {
        return mContext;
    }

    @Provides
    @Singleton
    AlbumContract.AlbumView providerView() {
        return mView;
    }

    @Provides
    @Singleton
    DefaultCameraModule providerDefaultCameraModule() {
        return new DefaultCameraModule();
    }


}
