package com.example.jessyuan.alldemo.githubapi;

import com.example.jessyuan.alldemo.Scoped.FragmentScoped;
import com.example.jessyuan.alldemo.component.NetworkComponent;
import com.example.jessyuan.alldemo.module.ApiServiceModule;

import dagger.Component;

/**
 * Created by JessYuan on 13/12/2016.
 */

@FragmentScoped
@Component(modules = {GithubModule.class, ApiServiceModule.class}, dependencies = NetworkComponent.class)
public interface GithubComponent {
    void inject(GithubFragment fragment);
}
