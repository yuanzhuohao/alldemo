package com.example.jessyuan.alldemo.album;

import com.example.jessyuan.alldemo.camera.DefaultCameraModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JessYuan on 13/12/2016.
 */

@Module
public class AlbumModule {

    private AlbumContract.AlbumView mView;

    public AlbumModule(AlbumContract.AlbumView view) {
        mView = view;
    }

    @Provides
    AlbumContract.AlbumView providerView() {
        return mView;
    }

    @Provides
    DefaultCameraModule providerDefaultCameraModule() {
        return new DefaultCameraModule();
    }

}
