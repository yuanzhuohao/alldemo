package com.example.jessyuan.alldemo.weather;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.api.WeatherService;
import com.example.jessyuan.alldemo.base.BaseToolbarFragment;
import com.example.jessyuan.alldemo.component.DaggerNetworkComponent;
import com.example.jessyuan.alldemo.component.NetworkComponent;
import com.example.jessyuan.alldemo.model.Image;
import com.example.jessyuan.alldemo.ui.ProgressDialogFragment;
import com.example.jessyuan.alldemo.model.Weather;
import com.example.jessyuan.alldemo.module.ApplicationModule;
import com.example.jessyuan.alldemo.module.NetworkModule;
import com.example.mylibrary.DateUtils;
import com.example.mylibrary.common.CommonRCLVAdapter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by JessYuan on 27/12/2016.
 */

public class WeatherFragment extends BaseToolbarFragment implements WeatherContract.WeatherView {

    private static final String TAG = "WeatherFragment";

    @BindView(R.id.tv_weather_city)
    TextView weatherCityTV;
    @BindView(R.id.tv_weather_weather)
    TextView weatherTV;
    @BindView(R.id.tv_weather_temperature)
    TextView temperatureTV;
    @BindView(R.id.tv_weather_date)
    TextView dateTV;
    @BindView(R.id.tv_weather_max_tmp)
    TextView maxTemperatureTV;
    @BindView(R.id.tv_weather_min_tmp)
    TextView minTemperatureTV;
    @BindView(R.id.rcv_daily_weather)
    RecyclerView dailyWeatherRV;
    @BindView(R.id.rcv_hourly_weather)
    RecyclerView hourlyWeatherRV;
    @BindView(R.id.tv_weather_tips)
    TextView weatherTipsTV;

    @BindView(R.id.tv_weather_sr)
    TextView srTV;
    @BindView(R.id.tv_weather_ss)
    TextView ssTV;
    @BindView(R.id.tv_weather_hum)
    TextView humTV;
    @BindView(R.id.tv_weather_wind)
    TextView windTV;
    @BindView(R.id.tv_weather_fl)
    TextView flTV;
    @BindView(R.id.tv_weather_pcpn)
    TextView pcpnTV;
    @BindView(R.id.tv_weather_pres)
    TextView presTV;
    @BindView(R.id.tv_weather_vis)
    TextView visTV;
    @BindView(R.id.tv_weather_uv)
    TextView uvTV;
    @BindView(R.id.tv_weather_api)
    TextView apiTV;
    @BindView(R.id.tv_weather_qlty)
    TextView qltyTV;
    @BindView(R.id.tv_weather_pm25)
    TextView pm25TV;

    @BindView(R.id.nsv_container)
    NestedScrollView mNestedScrollView;

    @Inject
    WeatherPresenter mPresenter;
    @Inject
    ProgressDialogFragment mProgressDialog;

    private CommonRCLVAdapter<Weather.DailyForecastBean> mDailyAdapter;
    private CommonRCLVAdapter<Weather.HourlyForecastBean> mHourlyAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setContentView(R.layout.fragment_weather);

        NetworkComponent networkComponent = DaggerNetworkComponent.builder()
                .applicationModule(new ApplicationModule(getActivity().getApplication()))
                .networkModule(new NetworkModule(WeatherService.BASE_URL))
                .build();

        DaggerWeatherComponent.builder()
                .weatherModule(new WeatherModule(getContext(), this))
                .networkComponent(networkComponent)
                .build()
                .inject(this);

        init();

        mPresenter.start();
    }

    private void init() {
        setAdapter();
        dailyWeatherRV.setLayoutManager(new LinearLayoutManager(getContext()));
        dailyWeatherRV.setAdapter(mDailyAdapter);
        dailyWeatherRV.setNestedScrollingEnabled(false);

        hourlyWeatherRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        hourlyWeatherRV.setAdapter(mHourlyAdapter);
        hourlyWeatherRV.setItemViewCacheSize(7);
    }

    private void setAdapter() {
        mDailyAdapter = new CommonRCLVAdapter<Weather.DailyForecastBean>(getContext(), R.layout.item_weather_daily, null) {
            @Override
            public void onBindViewHolder(CommonViewHolder holder, int position, Weather.DailyForecastBean data) {
                holder.getTextViewById(R.id.tv_item_weather_date).setText(DateUtils.getWeek(data.getDate(), "yyyy-MM-dd"));
                holder.getTextViewById(R.id.tv_item_weather_max_tmp).setText(data.getTmp().getMax() + "°");
                holder.getTextViewById(R.id.tv_item_weather_min_tmp).setText(data.getTmp().getMin() + "°");

                ImageView imageView = holder.getImageViewById(R.id.iv_item_weather_weather);
                TextView textView = holder.getTextViewById(R.id.tv_item_weather_weather);

                int res = matchIcon(data.getCond().getTxtD());
                if (res == 0) {
                    imageView.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(data.getCond().getTxtD());
                } else {
                    imageView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);
                    Glide.with(getContext())
                            .load(matchIcon(data.getCond().getTxtD()))
                            .into(imageView);
                }
            }
        };

        mHourlyAdapter = new CommonRCLVAdapter<Weather.HourlyForecastBean>(getContext(), R.layout.item_weather_hourly, null) {
            @Override
            public void onBindViewHolder(CommonViewHolder holder, int position, Weather.HourlyForecastBean data) {
                int space_index = data.getDate().indexOf(" ");
                ImageView imageView = holder.getImageViewById(R.id.iv_item_weather_sun);
                TextView textView = holder.getTextViewById(R.id.tv_item_weather_tmp);
                if (space_index == -1) {
                    holder.getTextViewById(R.id.tv_item_weather_time).setText(data.getDate());
                    imageView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);
                    Glide.with(getContext())
                            .load(data.getIconRes())
                            .into(imageView);
                } else {
                    holder.getTextViewById(R.id.tv_item_weather_time).setText(data.getDate().substring(space_index));
                    imageView.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(data.getTmp() + "°");
                }
            }
        };
    }

    @Override
    public void showTitle(String title) {
        getToolbar().setTitle(title);
    }

    @Override
    public void updateView(Weather weather) {
        mNestedScrollView.setVisibility(View.VISIBLE);

        weatherCityTV.setText(weather.getBasic().getCity());
        weatherTV.setText(weather.getNow().getCond().getTxt());
        temperatureTV.setText(weather.getNow().getTmp() + "°");
        maxTemperatureTV.setText(weather.getDailyForecast().get(0).getTmp().getMax() + "°");
        minTemperatureTV.setText(weather.getDailyForecast().get(0).getTmp().getMin() + "°");
        dateTV.setText(DateUtils.getWeek("", "yyyy-MM-dd") + "  今天");

        mHourlyAdapter.setData(weather.getHourlyForecast());
        mDailyAdapter.setData(weather.getDailyForecast().subList(1,7));

        ssTV.setText(weather.getDailyForecast().get(0).getAstro().getSs());
        srTV.setText(weather.getDailyForecast().get(0).getAstro().getSr());
        humTV.setText(weather.getNow().getPcpn() + " %");
        Weather.NowBean.WindBean wind = weather.getNow().getWind();
        windTV.setText(wind.getDir().substring(0, wind.getDir().length() -1)
                    + " " + wind.getSpd() + " km/h"
                    + " " + wind.getSc()
        );
        flTV.setText(weather.getNow().getFl() + "°");
        pcpnTV.setText(weather.getNow().getPcpn() + " mm");
        presTV.setText(weather.getNow().getPres() + " 百帕");
        visTV.setText(weather.getNow().getVis() + " km");
        uvTV.setText(weather.getSuggestion().getUv().getBrf());
        apiTV.setText(weather.getAqi().getCity().getAqi());
        qltyTV.setText(weather.getAqi().getCity().getQlty());
        pm25TV.setText(weather.getAqi().getCity().getPm25());
    }

    @Override
    public void updateTipsView(final String string) {
        weatherTipsTV.setText(string);

        ObjectAnimator animator = ObjectAnimator.ofFloat(weatherTipsTV, "alpha",0f, 1f);
        animator.setDuration(1500);
        animator.start();
    }

    @Override
    public void showLoading() {
        mProgressDialog.show(getFragmentManager(), null);
    }

    @Override
    public void dismissLoading() {
        mProgressDialog.dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.stop();
    }

    private int matchIcon(String weather) {
        if (weather.contains("多云")) {
            return R.drawable.cloudy;
        } else if (weather.contains("晴")) {
             return R.drawable.sunny;
        } else if (weather.contains("雨")) {
            return R.drawable.rain;
        } else if (weather.contains("雪")) {
            return R.drawable.snow;
        } else if (weather.contains("小雨")) {
            return R.drawable.small_rain;
        } else if (weather.contains("暴")) {
            return R.drawable.storm;
        } else if (weather.contains("雪")) {
            return R.drawable.snow;
        }

        return 0;
    };

}
