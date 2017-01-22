package com.example.jessyuan.alldemo.weather.weatherindex;

import com.example.jessyuan.alldemo.base.BasePresenter;
import com.example.jessyuan.alldemo.base.BaseView;
import com.example.jessyuan.alldemo.model.Weather;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JessYuan on 18/01/2017.
 */

public interface WeatherIndexContract {

    interface WeatherIndexView extends BaseView {
        void setPresenter(WeatherIndexPresenter presenter);
        void showTitle(String title);
        void updateAdapter(List<Weather> list);
    }


    interface WeatherIndexPresenter extends BasePresenter {
        void start();
        void startAddCityActivity();
        void stop();
    }

}
