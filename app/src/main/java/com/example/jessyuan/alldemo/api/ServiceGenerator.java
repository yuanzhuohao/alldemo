package com.example.jessyuan.alldemo.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JessYuan on 09/12/2016.
 */

public class ServiceGenerator {

    private static Retrofit mRetrofit;

    public ServiceGenerator(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    public static <T> T createService(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    public static final class Builder {
        private Retrofit.Builder mRetrofitBuilder;
        private OkHttpClient.Builder mClientBuilder;

        public Builder() {
            mClientBuilder = new OkHttpClient.Builder();
        }

        public Builder addBaseUrl(String url) {
            mRetrofitBuilder = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create());
            return this;
        }

        public ServiceGenerator build() {
            return new ServiceGenerator(mRetrofitBuilder.client(mClientBuilder.build()).build());
        }
    }

}
