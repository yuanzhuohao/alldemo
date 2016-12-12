package com.example.jessyuan.alldemo.githubapi;

import com.google.gson.JsonObject;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.example.jessyuan.alldemo.api.APIManager;
import com.example.jessyuan.alldemo.api.GithubService;
import com.example.jessyuan.alldemo.api.ServiceGenerator;
import com.example.jessyuan.alldemo.model.Repository;
import com.example.mylibrary.JsonParseUtils;
import com.example.mylibrary.LogUtils;

import java.util.ArrayList;
import java.util.List;

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

    GithubService mGithubService;
    private List<Repository> mRepositoryList = new ArrayList<>();

    private Runnable mRunnable;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public GithubPresenter(Context context, GithubContract.GithubView view) {
        mContext = context;
        mView = view;
        setGithubService();
    }

    private void setGithubService() {
        mGithubService = new ServiceGenerator.Builder().addBaseUrl(APIManager.GITHUB_API).build().createService(GithubService.class);
    }

    @Override
    public void start() {
        mView.showTitle("Github API");
    }

    @Override
    public void searchRepository(String text) {
        if (TextUtils.isEmpty(text)) {
            if (mRunnable != null) {
                mHandler.removeCallbacks(mRunnable);
            }

            mView.showRepository(null);
        }

        // avoid continuously request,so delay 1s for request
        mHandler.removeCallbacks(mRunnable);
        setRunnable(text);
        mHandler.postDelayed(mRunnable, 1000);
    }

    private void setRunnable(final String text) {
        mRunnable = new Runnable() {
            @Override
            public void run() {
                Call<JsonObject> call = mGithubService.searchRepositories(text, GithubService.SORT.starts);
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.body() == null) {
                            return;
                        }

                        mRepositoryList = JsonParseUtils.parseToArray(response.body().toString(), Repository[].class, "items");
                        LogUtils.i(TAG, "size: " + mRepositoryList.size());
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
