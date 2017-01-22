package com.example.jessyuan.alldemo.weather.weatherindex;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.base.BaseFragment;
import com.example.jessyuan.alldemo.base.BasePresenter;
import com.example.jessyuan.alldemo.base.BaseToolbarFragment;
import com.example.jessyuan.alldemo.model.Weather;
import com.example.jessyuan.alldemo.weather.WeatherContract;
import com.example.jessyuan.alldemo.weather.WeatherFragment;
import com.example.jessyuan.alldemo.weather.addcity.AddCityActivity;
import com.example.mylibrary.ToastUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by JessYuan on 10/01/2017.
 */

public class WeatherIndexFragment extends BaseFragment implements WeatherIndexContract.WeatherIndexView {

    @BindView(R.id.vp_weather_viewpager)
    ViewPager mViewPager;

    private MyAdapter mMyAdapter;

    private WeatherIndexContract.WeatherIndexPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_index, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mMyAdapter = new MyAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mMyAdapter);

        mPresenter.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.stop();
    }

    @Override
    public void showTitle(String title) {
        ((WeatherIndexActivity)getActivity()).getToolbar().setTitle("Weather");
    }

    @Override
    public void updateAdapter(List<Weather> list) {
        mMyAdapter.setData(list);
    }

    @Override
    public void setPresenter(WeatherIndexContract.WeatherIndexPresenter presenter) {
        mPresenter = presenter;
    }

    private static class MyAdapter extends FragmentPagerAdapter {

        private List<Weather> data;


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
            data.removeAll(Collections.singleton(null));
            return data.size();
        }

        public void setData(List<Weather> list) {
            data.clear();
            data.addAll(list);
            notifyDataSetChanged();
        }

    }

    @OnClick(R.id.fab_weather_add_city)
    void addCity() {
        mPresenter.startAddCityActivity();
    }




}
