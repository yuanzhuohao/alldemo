package com.example.jessyuan.alldemo.githubapi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JessYuan on 13/12/2016.
 */

@Module
public class GithubModule {

    private GithubContract.GithubView mView;

    public GithubModule(GithubContract.GithubView view) {
        mView = view;
    }

    @Provides
    GithubContract.GithubView provideView() {
        return mView;
    }
}
