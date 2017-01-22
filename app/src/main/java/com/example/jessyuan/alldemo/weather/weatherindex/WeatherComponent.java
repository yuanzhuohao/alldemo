package com.example.jessyuan.alldemo.weather.weatherindex;

import com.example.jessyuan.alldemo.Scoped.ActivityScoped;
import com.example.jessyuan.alldemo.Scoped.FragmentScoped;
import com.example.jessyuan.alldemo.component.NetworkComponent;
import com.example.jessyuan.alldemo.module.ApiServiceModule;
import com.example.jessyuan.alldemo.module.BaiduMapModule;
import com.example.jessyuan.alldemo.weather.WeatherFragment;

import dagger.Component;

/**
 * Created by JessYuan on 27/12/2016.
 */

@ActivityScoped
@Component(modules = {WeatherModule.class, ApiServiceModule.class, BaiduMapModule.class}, dependencies = {NetworkComponent.class})
public interface WeatherComponent {
    void inject(WeatherIndexActivity activity);
}
