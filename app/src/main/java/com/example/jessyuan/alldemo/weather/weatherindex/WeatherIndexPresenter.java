package com.example.jessyuan.alldemo.weather.weatherindex;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.text.TextUtils;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.jessyuan.alldemo.api.WeatherService;
import com.example.jessyuan.alldemo.helper.SPrefUtil;
import com.example.jessyuan.alldemo.model.Weather;
import com.example.jessyuan.alldemo.weather.addcity.AddCityActivity;
import com.example.mylibrary.JsonParseUtils;
import com.example.mylibrary.LogUtils;
import com.example.mylibrary.ToastUtils;
import com.example.permissionmanager.PermissionListener;
import com.example.permissionmanager.PermissionManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by JessYuan on 10/01/2017.
 */

public class WeatherIndexPresenter implements WeatherIndexContract.WeatherIndexPresenter {

    private static final String TAG = "WeatherIndexPresenter";

    public static final String KEY_CITY = "FAVOR_CITY";

    private List<String> mCitys;
    private List<Weather> mWeathers;

    @Inject
    WeatherService mWeatherService;
    @Inject
    LocationClient mLocationClient;
    @Inject
    LocationClientOption mClientOption;

    private Context mContext;
    private WeatherIndexContract.WeatherIndexView mView;

    @Inject
    public WeatherIndexPresenter(Context context, WeatherIndexContract.WeatherIndexView view) {
        mContext = context;
        mView = view;
    }

    @Inject
    void setupPresenter() {
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.showTitle("Weather App");

        mCitys = new ArrayList<>();

        mCitys.add(""); // first element of mCitys is empty string, insert empty string cause could insert location city for next operation
        String json = SPrefUtil.getString(mContext, KEY_CITY, "");
        if (!TextUtils.isEmpty(json)) {
            mCitys.addAll(new Gson().fromJson(json, List.class));
        }

        mWeathers = new ArrayList<>();

        startLocation();
    }

    private static final String ARGS_KEY = "WEATHERS";
    @Override
    public void startAddCityActivity() {
        Intent intent = new Intent(mContext, AddCityActivity.class);
        intent.putParcelableArrayListExtra(ARGS_KEY, (ArrayList<? extends Parcelable>) mWeathers);
        mContext.startActivity(intent);
    }

    private void startLocation() {
        mLocationClient.setLocOption(mClientOption); // init location
        setLocationListener();
        // quest permission location permission
        PermissionManager.askPermission(mContext, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                "Grant location permission can get location",
                new PermissionListener() {
                    @Override
                    public void onResult(String permission, boolean permissionGranted) {
                        if (permissionGranted) {
                            mLocationClient.start();
//                            mView.showLoading();
                        }

                    }
                });
    }

    /**
     * set Location Client callback listener
     */
    private void setLocationListener() {
        mLocationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                LogUtils.i(TAG, bdLocation.getCity() + " city id: " + bdLocation.getCityCode());
                if (bdLocation.getCity() == null) {
                    ToastUtils.makeTextShort(mContext, "location failure");
                    stopLocation();
                    return ;
                }

                if (TextUtils.isEmpty(mCitys.get(0))) { // insert location city to 1st position of city list.
                    mCitys.remove(0);
                    mCitys.add(0, removeChineseShi(bdLocation.getCity()));
                }

                queryCityWeather(); // query all city's weather of list

                stopLocation(); // request once location so stop location
            }
        });
    }

    private void queryCityWeather() {
        for (String city : mCitys) {
            queryWeather(city); // query each city's weather of list
        }
    }

    private void queryWeather(String city) {
        mWeatherService.weather(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    private Disposable mDisposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        LogUtils.i(TAG, jsonObject.toString());

                        final Weather weather = JsonParseUtils.parseToArray(jsonObject,
                                Weather[].class,
                                "HeWeather data service 3.0").get(0);

                        mView.updateAdapter(insertCityToRightPosition(weather));
                    }

                    @Override
                    public void onError(Throwable e) {
//                        mView.dismissLoading();
                        LogUtils.e(TAG, e.getMessage());
                        ToastUtils.makeTextShort(mContext, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        if (mDisposable != null && !mDisposable.isDisposed()) {
                            mDisposable.dispose();
                        }
                    }
                });
    }

    private void stopLocation() {
        mLocationClient.stop();
    }

    /**
     * because query weather api is asynchronous, we could insert each weather to right position of
     * List
     * @param weather
     * @return
     */
    private List<Weather> insertCityToRightPosition(Weather weather) {
        synchronized (mWeathers) {
            int i = findIndexOfCityList(weather);
            if (mWeathers.size() == 0) {
                mWeathers.add(weather);
            } else if (i != -1 && i < mWeathers.size()) {
                mWeathers.remove(i);
                mWeathers.add(i, weather);
            } else if (i != -1 && i >= mWeathers.size()) {
                mWeathers.add(weather);
            }
        }

        return mWeathers;
    }

    private int findIndexOfCityList(Weather weather) {
        int index = -1;

        for (int i = 0; i < mCitys.size(); i++) {
            if (mCitys.get(i).equals(weather.getBasic().getCity())) {
                index = i;
                break;
            }
        }

        return index;
    }


    /**
     * remove city name '市' character
     * @param city
     */
    private String removeChineseShi(String city) {
        return city.contains("市") ? city.substring(0, city.length() - 1) : city;
    }

    @Override
    public void stop() {
        stopLocation();
    }


}
