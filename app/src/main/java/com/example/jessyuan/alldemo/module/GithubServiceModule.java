package com.example.jessyuan.alldemo.module;

import com.example.jessyuan.alldemo.Scoped.GithubScoped;
import com.example.jessyuan.alldemo.api.GithubService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by JessYuan on 15/12/2016.
 */

@Module
public class GithubServiceModule {

    @Provides
    @GithubScoped
    GithubService provideGithubService(Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }

}
