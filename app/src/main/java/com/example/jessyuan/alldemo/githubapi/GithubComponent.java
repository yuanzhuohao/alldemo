package com.example.jessyuan.alldemo.githubapi;

import com.example.jessyuan.alldemo.ApplicationComponent;
import com.example.jessyuan.alldemo.helper.FragmentScoped;

import dagger.Component;

/**
 * Created by JessYuan on 13/12/2016.
 */

@FragmentScoped
@Component(dependencies = ApplicationComponent.class, modules = GithubModule.class)
public interface GithubComponent {
    void inject(GithubFragment fragment);
}
