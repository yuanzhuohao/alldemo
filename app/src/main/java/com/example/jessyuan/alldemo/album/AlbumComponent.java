package com.example.jessyuan.alldemo.album;

import com.example.jessyuan.alldemo.module.ImageLoaderModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by JessYuan on 13/12/2016.
 */

@Singleton
@Component(modules = {AlbumModule.class, ImageLoaderModule.class})
public interface AlbumComponent {
    void inject(AlbumFragment fragment);
}
