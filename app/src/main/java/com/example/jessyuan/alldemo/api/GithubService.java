package com.example.jessyuan.alldemo.api;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by JessYuan on 09/12/2016.
 */

public interface GithubService {

    public static final String GITHUB_API = "https://api.github.com";

    @GET("/search/repositories")
    Call<JsonObject> searchRepositories(@Query("q") String kw);

    @GET("/search/repositories")
    Call<JsonObject> searchRepositories(@Query("q") String kw, @Query("sort") String sort);

    @GET("/search/users")
    Call<JsonObject> searchUsers(@Query("q") String username);

    @GET("/search/users")
    Call<JsonObject> searchUsers(@Query("q") String username, @Query("sort") String sort);

}
