package com.example.jessyuan.alldemo.weather;

import android.content.Context;

import com.example.jessyuan.alldemo.Scoped.FragmentScoped;
import com.example.jessyuan.alldemo.ui.ProgressDialogFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JessYuan on 27/12/2016.
 */

@Module
public class WeatherModule {
    private WeatherContract.WeatherView mView;
    private Context mContext;

    public WeatherModule(Context context, WeatherContract.WeatherView view) {
        mContext = context;
        mView = view;
    }

    @Provides
    @FragmentScoped
    WeatherContract.WeatherView provideView() {
        return mView;
    }

    @Provides
    @FragmentScoped
    Context provideContext() {
        return mContext;
    }

    @Provides
    @FragmentScoped
    ProgressDialogFragment provideProgressDialog() {
        return new ProgressDialogFragment();
    }
}
