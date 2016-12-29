package com.example.jessyuan.alldemo.api;

import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by JessYuan on 27/12/2016.
 */

public interface WeatherService {
    public static final String BASE_URL = "http://apis.baidu.com";

    @Headers("apikey: 69316a118a4198221e63b9fe151315d8")
    @GET("/heweather/pro/weather")
    Observable<JsonObject> weather(@Query("city") String city);

}
