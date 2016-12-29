package com.example.jessyuan.alldemo.component;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.jessyuan.alldemo.Scoped.BaiduMapScoped;
import com.example.jessyuan.alldemo.base.BasePresenter;
import com.example.jessyuan.alldemo.module.BaiduMapModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;

/**
 * Created by JessYuan on 28/12/2016.
 */

@Singleton
@Component(modules = BaiduMapModule.class)
public interface BaiduComponent {
    void inject(BasePresenter presenter);
}
