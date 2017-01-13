package com.example.jessyuan.alldemo.module;

import android.content.Context;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.jessyuan.alldemo.Scoped.FragmentScoped;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JessYuan on 28/12/2016.
 */

@Module
public class BaiduMapModule {

    @Provides
    @FragmentScoped
    LocationClient provideLocationClient(Context context) {
        return new LocationClient(context);
    }

    @Provides
    @FragmentScoped
    LocationClientOption provideClientOption() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setScanSpan(8000); // 定位请求间隔
        option.setIsNeedAddress(true); // 是否需要地址信息
        option.setLocationNotify(true); // 是否当GPS有效时按1s/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true); // 是否需要地址语义化结果
        option.setIsNeedLocationPoiList(true); // 是否得到POI结果
        option.setIgnoreKillProcess(false); // 定位SDK内部的一个service
        option.setEnableSimulateGps(false);

        return option;
    }
}
