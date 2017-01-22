package com.example.jessyuan.alldemo.weather;

import com.example.jessyuan.alldemo.base.BasePresenter;
import com.example.jessyuan.alldemo.base.BaseView;
import com.example.jessyuan.alldemo.model.Weather;

import java.util.List;

/**
 * Created by JessYuan on 27/12/2016.
 */

public interface WeatherContract {
    interface WeatherView extends BaseView {
        void updateView(Weather weather);
        void updateTipsView(String string);
    }

    interface WeatherPresenter extends BasePresenter {
        void start();
        void setTip(Weather.SuggestionBean suggestion);
        void stop();
    }
}
