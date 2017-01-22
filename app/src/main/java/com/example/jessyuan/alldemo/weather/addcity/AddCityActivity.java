package com.example.jessyuan.alldemo.weather.addcity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.base.BaseToolbarActivity;
import com.example.jessyuan.alldemo.model.Weather;
import com.example.mylibrary.FragmentUtils;

/**
 * Created by JessYuan on 22/01/2017.
 */

public class AddCityActivity extends BaseToolbarActivity {

    private static final String ARGS_KEY = "WEATHERS";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getToolbar().setTitle("Add City");

        AddCityFragment fragment = AddCityFragment.newInstance(getIntent().<Weather>getParcelableArrayListExtra(ARGS_KEY));
        FragmentUtils.addFragmentToActivity(getSupportFragmentManager(),
                fragment,
                R.id.fl_content);


    }
}
