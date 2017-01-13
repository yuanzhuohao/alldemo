package com.example.jessyuan.alldemo.weather;

import com.google.gson.JsonObject;

import android.Manifest;
import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.api.WeatherService;
import com.example.jessyuan.alldemo.model.Weather;
import com.example.mylibrary.JsonParseUtils;
import com.example.mylibrary.LogUtils;
import com.example.mylibrary.ToastUtils;
import com.example.permissionmanager.PermissionListener;
import com.example.permissionmanager.PermissionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by JessYuan on 28/12/2016.
 */

public class WeatherPresenter implements WeatherContract.WeatherPresenter {

    private static final String TAG = "WeatherPresenter";

    @Inject
    WeatherService mWeatherService;
    @Inject
    LocationClient mLocationClient;
    @Inject
    LocationClientOption mClientOption;

    private WeatherContract.WeatherView mView;
    private Context mContext;
    private Disposable mDisposable;

    @Inject
    public WeatherPresenter(Context context, WeatherContract.WeatherView view) {
        mContext = context;
        mView = view;
    }

    @Override
    public void start() {
        mView.showTitle("Weather App");
    }

    @Override
    public void startLocation() {
        // init location
        mLocationClient.setLocOption(mClientOption);
        setLocationListener();
        PermissionManager.askPermission(mContext, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                "Grant location permission can get location",
                new PermissionListener() {
                    @Override
                    public void onResult(String permission, boolean permissionGranted) {
                        if (permissionGranted) {
                            mLocationClient.start();
                            mView.showLoading();
                        }

                    }
                });
    }

    @Override
    public void queryWeather(String city) {
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

                        mView.dismissLoading();

                        final Weather weather = JsonParseUtils.parseToArray(jsonObject,
                                Weather[].class,
                                "HeWeather data service 3.0").get(0);

                        setSunRiseOrSunSet(weather);
                        mView.updateView(weather);
                        setTip(weather.getSuggestion());
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissLoading();
                        LogUtils.d(TAG, e.getMessage());
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


    /**
     * Set Tips TextView was polling by RxJava
     * @param suggestion
     */
    private void setTip(Weather.SuggestionBean suggestion) {
        final List<String> tips = new ArrayList<>();
        tips.add(suggestion.getAir().getTxt());
        tips.add(suggestion.getComf().getTxt());
        tips.add(suggestion.getCw().getTxt());
        tips.add(suggestion.getDrsg().getTxt());
        tips.add(suggestion.getFlu().getTxt());
        tips.add(suggestion.getSport().getTxt());
        tips.add(suggestion.getTrav().getTxt());
        tips.add(suggestion.getUv().getTxt());

        mDisposable = Observable.interval(0, 10000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        int idx = (int) (aLong % 8);
                        LogUtils.i(TAG, tips.get(idx));
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("温馨提示: ");
                        stringBuffer.append(tips.get(idx));
                        mView.updateTipsView(stringBuffer.toString());
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
                    mView.dismissLoading();
                    ToastUtils.makeTextShort(mContext, "定位失败");
                }
                queryWeather(bdLocation.getCity());
                stopLocation();
            }
        });
    }

    /**
     * Insert two HourlyForecastBean into list, includs sunrise and sunset
     * @param weather
     * @return
     */
    private Weather setSunRiseOrSunSet(Weather weather) {
        List<Weather.HourlyForecastBean> list = weather.getHourlyForecast();
        String string_ss = weather.getDailyForecast().get(0).getAstro().getSs();
        String string_sr = weather.getDailyForecast().get(0).getAstro().getSr();
        // it use split hours and mins value
        int index_ss = string_ss.indexOf(":");
        int index_sr = string_sr.indexOf(":");

        int i_ss = Integer.valueOf(string_ss.substring(0, index_ss));
        int i_sr = Integer.valueOf(string_sr.substring(0, index_sr));

        // index to insert list
        int insertIdxSr = -1;
        int insertIdxSs = -1;

        for (int i = 1; i < list.size(); i++) {
            String dateStr1 = list.get(i - 1).getDate();
            String dateStr2 = list.get(i).getDate();

            int spaceIdx = dateStr1.indexOf(" ");
            int colIdx = dateStr1.indexOf(":");

            int int1 = Integer.valueOf(dateStr1.substring(spaceIdx + 1, colIdx));
            int int2 = Integer.valueOf(dateStr2.substring(spaceIdx + 1, colIdx));

            if (i_ss >= int1 && i_ss < int2) {
                insertIdxSs = i;
            }

            if (i_sr >= int1 && i_sr < int2) {
                insertIdxSr = i;
            }
        }

        // insert two bean into list
        if (insertIdxSr != -1 && insertIdxSs != -1) {
            Weather.HourlyForecastBean sunset = new Weather.HourlyForecastBean();
            sunset.setDate(string_ss);
            sunset.setIconRes(R.drawable.sunset);
            Weather.HourlyForecastBean sunrise = new Weather.HourlyForecastBean();
            sunrise.setDate(string_sr);
            sunrise.setIconRes(R.drawable.sunrise);

            list.add(insertIdxSr, sunrise);
            list.add(insertIdxSs, sunset);

        }

        weather.setHourlyForecast(list);
        return weather;
    }

    @Override
    public void stopLocation() {
        mLocationClient.stop();
    }

    @Override
    public void stop() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

}
