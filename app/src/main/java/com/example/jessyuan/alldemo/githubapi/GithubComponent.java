package com.example.jessyuan.alldemo.githubapi;

import com.example.jessyuan.alldemo.Scoped.GithubScoped;
import com.example.jessyuan.alldemo.component.NetworkComponent;
import com.example.jessyuan.alldemo.module.GithubServiceModule;

import dagger.Component;

/**
 * Created by JessYuan on 13/12/2016.
 */

@GithubScoped
@Component(modules = {GithubModule.class, GithubServiceModule.class}, dependencies = NetworkComponent.class)
public interface GithubComponent {
    void inject(GithubFragment fragment);
}
