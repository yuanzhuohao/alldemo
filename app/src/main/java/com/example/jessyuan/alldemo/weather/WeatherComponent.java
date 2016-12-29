package com.example.jessyuan.alldemo.weather;

import com.example.jessyuan.alldemo.Scoped.FragmentScoped;
import com.example.jessyuan.alldemo.component.NetworkComponent;
import com.example.jessyuan.alldemo.module.ApiServiceModule;
import com.example.jessyuan.alldemo.module.BaiduMapModule;

import dagger.Component;

/**
 * Created by JessYuan on 27/12/2016.
 */

@FragmentScoped
@Component(modules = {WeatherModule.class, ApiServiceModule.class, BaiduMapModule.class}, dependencies = {NetworkComponent.class})
public interface WeatherComponent {
    void inject(WeatherFragment fragment);
}
