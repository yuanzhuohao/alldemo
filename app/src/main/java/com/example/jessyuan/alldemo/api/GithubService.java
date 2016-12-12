package com.example.jessyuan.alldemo.api;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by JessYuan on 09/12/2016.
 */

public interface GithubService {

    public static enum SORT {
        starts, forks, updated
    }

    @GET("/search/repositories")
    Call<JsonObject> searchRepositories(@Query("q") String kw, @Query("sort") SORT sort);
}
