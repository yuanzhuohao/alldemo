package com.example.jessyuan.alldemo.githubapi;

import com.example.jessyuan.alldemo.base.BasePresenter;
import com.example.jessyuan.alldemo.base.BaseView;
import com.example.jessyuan.alldemo.model.Repository;

import java.util.List;

/**
 * Created by JessYuan on 12/12/2016.
 */

public interface GithubContract {
    interface GithubView extends BaseView {
        void showTitle(String str);
        void showRepository(List<Repository> list);
    }

    interface GithubPresenter extends BasePresenter {
        void searchRepository(String text);
    }
}
