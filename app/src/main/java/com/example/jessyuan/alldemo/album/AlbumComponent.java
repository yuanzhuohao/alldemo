package com.example.jessyuan.alldemo.album;

import com.example.jessyuan.alldemo.ApplicationComponent;
import com.example.jessyuan.alldemo.helper.FragmentScoped;

import dagger.Component;

/**
 * Created by JessYuan on 13/12/2016.
 */

@FragmentScoped
@Component(dependencies = ApplicationComponent.class, modules = AlbumModule.class)
public interface AlbumComponent {
    void inject(AlbumFragment fragment);
}
