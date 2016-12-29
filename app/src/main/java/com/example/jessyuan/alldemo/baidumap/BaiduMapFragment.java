package com.example.jessyuan.alldemo.baidumap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.base.BaseToolbarFragment;

import butterknife.BindView;

/**
 * Created by JessYuan on 27/12/2016.
 */

public class BaiduMapFragment extends BaseToolbarFragment {

    @BindView(R.id.baidu_map)
    MapView mMapView;

    BaiduMap mBaiduMap;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SDKInitializer.initialize(getActivity().getApplicationContext());
        setContentView(R.layout.fragment_baidumap);

        mBaiduMap = mMapView.getMap();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
}
