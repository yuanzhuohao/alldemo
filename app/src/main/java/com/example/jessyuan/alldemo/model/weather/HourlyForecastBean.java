package com.example.jessyuan.alldemo.model.weather;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by JessYuan on 10/03/2017.
 */
public class HourlyForecastBean extends RealmObject {
    /**
     * date : 2016-12-27 16:00
     * hum : 53
     * pop : 0
     * pres : 1022
     * tmp : 18
     * wind : {"deg":"25","dir":"东北风","sc":"3-4","spd":"24"}
     */

    @SerializedName("date")
    private String date;
    @SerializedName("hum")
    private String hum;
    @SerializedName("pop")
    private String pop;
    @SerializedName("pres")
    private String pres;
    @SerializedName("tmp")
    private String tmp;
    @SerializedName("wind")
    private WindBeanXX wind;
    private int iconRes; // 是否太阳升起

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public WindBeanXX getWind() {
        return wind;
    }

    public void setWind(WindBeanXX wind) {
        this.wind = wind;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

}
