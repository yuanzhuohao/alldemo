package com.example.jessyuan.alldemo.module;

import com.example.jessyuan.alldemo.Scoped.FragmentScoped;
import com.example.jessyuan.alldemo.Scoped.WeatherScoped;
import com.example.jessyuan.alldemo.api.GithubService;
import com.example.jessyuan.alldemo.api.WeatherService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/** This module provide API Service interface
 * Created by JessYuan on 15/12/2016.
 */

@Module
public class ApiServiceModule {

    @Provides
    @FragmentScoped
    GithubService provideGithubService(Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }

    @Provides
    @FragmentScoped
    WeatherService provideWeather(Retrofit retrofit) {
        return retrofit.create(WeatherService.class);
    }

}
