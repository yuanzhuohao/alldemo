package com.example.jessyuan.alldemo.model.weather;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by JessYuan on 10/03/2017.
 */
public class AirBean extends RealmObject {
    /**
     * brf : 良
     * txt : 气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。
     */

    @SerializedName("brf")
    private String brf;
    @SerializedName("txt")
    private String txt;

    public String getBrf() {
        return brf;
    }

    public void setBrf(String brf) {
        this.brf = brf;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
