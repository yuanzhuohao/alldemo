package com.example.jessyuan.alldemo.weather.weatherindex;

import android.content.Context;

import com.example.jessyuan.alldemo.Scoped.ActivityScoped;
import com.example.jessyuan.alldemo.Scoped.FragmentScoped;
import com.example.jessyuan.alldemo.ui.ProgressDialogFragment;
import com.example.jessyuan.alldemo.weather.WeatherContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JessYuan on 27/12/2016.
 */

@Module
public class WeatherModule {
    private WeatherIndexContract.WeatherIndexView mView;
    private Context mContext;

    public WeatherModule(Context context, WeatherIndexContract.WeatherIndexView view) {
        mContext = context;
        mView = view;
    }

    @Provides
    @ActivityScoped
    WeatherIndexContract.WeatherIndexView provideView() {
        return mView;
    }

    @Provides
    @ActivityScoped
    Context provideContext() {
        return mContext;
    }
}
