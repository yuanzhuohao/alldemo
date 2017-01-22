package com.example.jessyuan.alldemo.weather.addcity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.base.BaseFragment;
import com.example.jessyuan.alldemo.model.Weather;
import com.example.mylibrary.common.CommonRCLVAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by JessYuan on 22/01/2017.
 */

public class AddCityFragment extends BaseFragment {

    private static final String ARGS_KEY = "WEATHERS";

    @BindView(R.id.et_add_city_name)
    EditText mEditText;
    @BindView(R.id.rcv_all_cities)
    RecyclerView mRecyclerView;

    private CommonRCLVAdapter<Weather> mAdapter;

    private List<Weather> data;

    public static AddCityFragment newInstance(ArrayList<Weather> list) {

        Bundle args = new Bundle();

        AddCityFragment fragment = new AddCityFragment();
        args.putParcelableArrayList(ARGS_KEY, list);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_city, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new CommonRCLVAdapter<Weather>(getContext(), R.layout.item_add_city_weather,null) {
            @Override
            public void onBindViewHolder(CommonViewHolder holder, int position, Weather data) {
                holder.getTextViewById(R.id.tv_item_add_city_name).setText(data.getBasic().getCity());
                holder.getTextViewById(R.id.tv_item_add_city_weather).setText(data.getNow().getCond().getTxt());
                holder.getTextViewById(R.id.tv_item_add_city_temperature).setText(data.getNow().getTmp() + "℃");
            }
        };

        mRecyclerView.setAdapter(mAdapter);

        init();
    }

    private void init() {
        mAdapter.setData(getArguments().<Weather>getParcelableArrayList(ARGS_KEY));
    }

    @OnClick(R.id.btn_add_city)
    void addCity() {

    }

}
