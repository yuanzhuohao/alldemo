package com.example.jessyuan.alldemo.githubapi;

import android.app.Activity;
import android.content.Context;

import com.example.jessyuan.alldemo.api.GithubService;
import com.example.jessyuan.alldemo.base.BasePresenter;
import com.example.jessyuan.alldemo.base.BaseView;
import com.example.jessyuan.alldemo.model.Repository;
import com.example.jessyuan.alldemo.model.User;

import java.util.List;

/**
 * Created by JessYuan on 12/12/2016.
 */

public interface GithubContract {
    interface GithubView extends BaseView {
        void showTitle(String str);
        void showRepository(List<Repository> list);
        void showUser(List<User> list);
    }

    interface GithubPresenter extends BasePresenter {
        void searchRepository(String text);
        void searchRepository(String text, String sorted);
        void searchUser(String text);
        void searchUser(String text, String sorted);
        void openRepository(Repository rep, Activity activity);
        void openUser(User user, Activity activity);
    }
}
