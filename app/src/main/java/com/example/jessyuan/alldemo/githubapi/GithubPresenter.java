package com.example.jessyuan.alldemo.githubapi;

import com.google.gson.JsonObject;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.example.jessyuan.alldemo.api.GithubService;
import com.example.jessyuan.alldemo.api.ServiceGenerator;
import com.example.jessyuan.alldemo.model.Repository;
import com.example.jessyuan.alldemo.model.User;
import com.example.mylibrary.JsonParseUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JessYuan on 12/12/2016.
 */

public class GithubPresenter implements GithubContract.GithubPresenter {

    private static final String TAG = "GithubPresenter";

    GithubContract.GithubView mView;
    private Context mContext;

    private GithubService mGithubService;
    private List<Repository> mRepositoryList = new ArrayList<>();
    private List<User> mUserList = new ArrayList<>();

    private Runnable mRunnable;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Inject
    public GithubPresenter(Context context, GithubContract.GithubView view) {
        mContext = context;
        mView = view;
    }

    @Inject
    void setGithubService() {
        mGithubService = new ServiceGenerator.Builder().addBaseUrl(GithubService.GITHUB_API).build().createService(GithubService.class);
    }

    @Override
    public void start() {
        mView.showTitle("Github API");
    }

    @Override
    public void searchRepository(String text) {
        if (mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
        }

        if (TextUtils.isEmpty(text)) {
            mView.showRepository(null);
        }

        // avoid continuously request,so delay 1s for request
        Call<JsonObject> call = mGithubService.searchRepositories(text);
        setRepositoryRunnable(call);
        mHandler.postDelayed(mRunnable, 1000);
    }

    @Override
    public void searchRepository(String text, String sorted) {
        if (mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
        }

        if (TextUtils.isEmpty(text)) {
            mView.showRepository(null);
        }

        // avoid continuously request,so delay 1s for request
        Call<JsonObject> call = mGithubService.searchRepositories(text, sorted);
        setRepositoryRunnable(call);
        mHandler.postDelayed(mRunnable, 1000);
    }

    @Override
    public void searchUser(String text) {
        if (mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
        }

        if (TextUtils.isEmpty(text)) {
            mView.showRepository(null);
        }

        // avoid continuously request,so delay 1s for request
        Call<JsonObject> call = mGithubService.searchUsers(text);
        setUserRunnable(call);
        mHandler.postDelayed(mRunnable, 1000);
    }

    @Override
    public void searchUser(String text, String sorted) {
        if (mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
        }

        if (TextUtils.isEmpty(text)) {
            mView.showRepository(null);
        }

        // avoid continuously request,so delay 1s for request
        Call<JsonObject> call = mGithubService.searchUsers(text, sorted);
        setUserRunnable(call);
        mHandler.postDelayed(mRunnable, 1000);
    }

    @Override
    public void openRepository(Repository rep) {

    }

    private void setUserRunnable(final Call call) {
        mRunnable = new Runnable() {
            @Override
            public void run() {
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        if (response.body() == null) {
                            return;
                        }

                        mUserList = JsonParseUtils.parseToArray(response.body().toString(),
                                User[].class,
                                "items");
                        mView.showUser(mUserList);
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
            }
        };
    }

    private void setRepositoryRunnable(final Call call) {
        mRunnable = new Runnable() {
            @Override
            public void run() {
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.body() == null) {
                            return;
                        }

                        mRepositoryList = JsonParseUtils.parseToArray(response.body().toString(),
                                Repository[].class,
                                "items");
                        mView.showRepository(mRepositoryList);
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        };
    }
}
