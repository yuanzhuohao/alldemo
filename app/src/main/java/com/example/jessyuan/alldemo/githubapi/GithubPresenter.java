package com.example.jessyuan.alldemo.githubapi;

import com.google.gson.JsonObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.example.jessyuan.alldemo.api.GithubService;
import com.example.jessyuan.alldemo.model.Repository;
import com.example.jessyuan.alldemo.model.User;
import com.example.jessyuan.alldemo.webview.WebViewActivity;
import com.example.mylibrary.JsonParseUtils;
import com.example.mylibrary.LogUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JessYuan on 12/12/2016.
 */

public class GithubPresenter implements GithubContract.GithubPresenter {

    private static final String TAG = "GithubPresenter";

    GithubContract.GithubView mView;

    private List<Repository> mRepositoryList = new ArrayList<>();
    private List<User> mUserList = new ArrayList<>();

    @Inject
    Handler mHandler;
    @Inject
    GithubService mGithubService;

    @Inject
    public GithubPresenter(GithubContract.GithubView view) {
        mView = view;
    }

    @Override
    public void start() {
        mView.showTitle("Github API");
    }

    @Override
    public void searchRepository(String text) {
        if (TextUtils.isEmpty(text)) {
            mView.showRepository(null);
        }

         mGithubService.searchRepositories(text)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<JsonObject>() {
                        private Disposable mDisposable;

                        @Override
                        public void onSubscribe(Disposable d) {
                            mDisposable = d;
                        }

                        @Override
                        public void onNext(JsonObject response) {
                            if (response == null) {
                                return;
                            }

                            mRepositoryList = JsonParseUtils.parseToArray(response.toString(),
                                    Repository[].class,
                                    "items");
                            mView.showRepository(mRepositoryList);
                        }

                        @Override
                        public void onError(Throwable e) {
                            LogUtils.e(TAG, "Github APi Error: " + e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            if (mDisposable != null && !mDisposable.isDisposed()) {
                                mDisposable.dispose();
                            }
                        }
                    });
    }

    @Override
    public void searchRepository(String text, String sorted) {
        if (TextUtils.isEmpty(text)) {
            mView.showRepository(null);
        }

        mGithubService.searchRepositories(text, sorted)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject response) {
                        if (response == null) {
                            return;
                        }

                        mRepositoryList = JsonParseUtils.parseToArray(response.toString(),
                                Repository[].class,
                                "items");
                        mView.showRepository(mRepositoryList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "Github APi Error: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void searchUser(String text) {
        if (TextUtils.isEmpty(text)) {
            mView.showRepository(null);
        }

        // avoid continuously request,so delay 1s for request
        mGithubService.searchUsers(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        if (jsonObject == null) {
                            return;
                        }

                        mUserList = JsonParseUtils.parseToArray(jsonObject.toString(),
                                User[].class,
                                "items");
                        mView.showUser(mUserList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void searchUser(String text, String sorted) {
        if (TextUtils.isEmpty(text)) {
            mView.showRepository(null);
        }

        mGithubService.searchUsers(text, sorted)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        if (jsonObject == null) {
                            return;
                        }

                        mUserList = JsonParseUtils.parseToArray(jsonObject.toString(),
                                User[].class,
                                "items");
                        mView.showUser(mUserList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void openUser(User user, Activity activity) {
        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra("url", user.getHtmlUrl());
        intent.putExtra("title", "Github");
        activity.startActivity(intent);
    }

    @Override
    public void openRepository(Repository rep, Activity activity) {
        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra("url", rep.getHtmlUrl());
        intent.putExtra("title", "Github");
        activity.startActivity(intent);

    }
}
