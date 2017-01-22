package com.example.jessyuan.alldemo.weather.weatherindex;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.api.WeatherService;
import com.example.jessyuan.alldemo.base.BaseToolbarActivity;
import com.example.jessyuan.alldemo.component.DaggerNetworkComponent;
import com.example.jessyuan.alldemo.component.NetworkComponent;
import com.example.jessyuan.alldemo.module.ApplicationModule;
import com.example.jessyuan.alldemo.module.NetworkModule;
import com.example.mylibrary.FragmentUtils;

import javax.inject.Inject;

/**
 * Created by JessYuan on 19/01/2017.
 */

public class WeatherIndexActivity extends BaseToolbarActivity {

    @Inject
    WeatherIndexPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WeatherIndexFragment fragment = (WeatherIndexFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fl_content);

        if (fragment == null) {
            fragment = new WeatherIndexFragment();

            FragmentUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment,
                    R.id.fl_content);
        }

        getToolbar().setTitle("Weather");

        NetworkComponent networkComponent = DaggerNetworkComponent.builder()
                .applicationModule(new ApplicationModule(getApplication()))
                .networkModule(new NetworkModule(WeatherService.Weather_BASE_URL))
                .build();

        DaggerWeatherComponent.builder().networkComponent(networkComponent)
                .weatherModule(new WeatherModule(this, fragment))
                .build().inject(this);
    }
}
