package com.example.jessyuan.alldemo.component;

import com.example.jessyuan.alldemo.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by JessYuan on 23/01/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

}
