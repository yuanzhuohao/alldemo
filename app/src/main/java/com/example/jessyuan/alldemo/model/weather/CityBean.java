package com.example.jessyuan.alldemo.model.weather;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by JessYuan on 10/03/2017.
 */
public class CityBean extends RealmObject {
    /**
     * aqi : 26
     * co : 1
     * no2 : 21
     * o3 : 55
     * pm10 : 25
     * pm25 : 9
     * qlty : 优
     * so2 : 7
     */

    @SerializedName("aqi")
    private String aqi;
    @SerializedName("pm25")
    private String pm25;
    @SerializedName("qlty")
    private String qlty;

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getQlty() {
        return qlty;
    }

    public void setQlty(String qlty) {
        this.qlty = qlty;
    }
}
