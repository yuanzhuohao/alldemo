package com.example.jessyuan.alldemo.model.weather;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by JessYuan on 10/03/2017.
 */
public class AqiBean extends RealmObject {
    /**
     * city : {"aqi":"26","co":"1","no2":"21","o3":"55","pm10":"25","pm25":"9","qlty":"优","so2":"7"}
     */

    @SerializedName("city")
    private CityBean city;

    public CityBean getCity() {
        return city;
    }

    public void setCity(CityBean city) {
        this.city = city;
    }

}
