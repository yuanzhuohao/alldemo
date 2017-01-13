package com.example.jessyuan.alldemo.weather;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;


import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.base.BaseToolbarFragment;
import com.example.jessyuan.alldemo.helper.SPrefUtil;
import com.example.mylibrary.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by JessYuan on 10/01/2017.
 */

public class WeatherIndexFragment extends BaseToolbarFragment implements WeatherContract.WeatherIndexView {

    @BindView(R.id.vp_weather_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.fab_weather_add_city)
    FloatingActionButton mButton;

    private MyAdapter mMyAdapter;

    private WeatherIndexPresenter mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setContentView(R.layout.fragment_weather_index);

        mPresenter = new WeatherIndexPresenter(getContext(), this);


        mMyAdapter = new MyAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mMyAdapter);

        mPresenter.start();
    }

    @Override
    public void showCityWeather(List<String> list) {
        mMyAdapter.setData(list);
    }

    private static class MyAdapter extends FragmentPagerAdapter {

        private List<String> data;


        public MyAdapter(FragmentManager fm) {
            super(fm);
            data = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) {
            return WeatherFragment.newInstance(data.get(position));
        }

        @Override
        public int getCount() {
            return data.size();
        }

        public void setData(List<String> list) {
            data.addAll(list);
            notifyDataSetChanged();
        }
    }

    @OnClick(R.id.fab_weather_add_city)
    void addCity() {
        mPresenter.saveCity("东莞");
        ToastUtils.makeTextShort(getContext(), "Add City!");

    }
}
