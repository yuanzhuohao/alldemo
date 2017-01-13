package com.example.jessyuan.alldemo.weather;

import com.google.gson.Gson;

import android.content.Context;
import android.text.TextUtils;

import com.example.jessyuan.alldemo.helper.SPrefUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JessYuan on 10/01/2017.
 */

public class WeatherIndexPresenter implements WeatherContract.WeatherIndexPresenter {

    public static final String KEY_CITY = "FAVOR_CITY";

    private List<String> mCitys;

    private Context mContext;
    private WeatherContract.WeatherIndexView mView;

    public WeatherIndexPresenter(Context context, WeatherContract.WeatherIndexView view) {
        mContext = context;
        mView = view;
    }

    @Override
    public void start() {
        mCitys = new ArrayList<>();
        mCitys.add("");
        String json = SPrefUtil.getString(mContext, KEY_CITY, "");
        if (!TextUtils.isEmpty(json)) {
            mCitys.addAll(new Gson().fromJson(json, List.class));
        }

        mView.showCityWeather(mCitys);
    }


    @Override
    public void saveCity(String city) {
        mCitys.add(city);

        List<String> temp = mCitys;
        temp.remove(0);
        SPrefUtil.put(mContext, KEY_CITY, new Gson().toJson(temp));

        mView.showCityWeather(mCitys);
    }
}
