package com.example.jessyuan.alldemo.model.weather;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by JessYuan on 10/03/2017.
 */
public class WindBeanX extends RealmObject {
    /**
     * deg : 21
     * dir : 北风
     * sc : 3-4
     * spd : 15
     */

    @SerializedName("deg")
    private String deg;
    @SerializedName("dir")
    private String dir;
    @SerializedName("sc")
    private String sc;
    @SerializedName("spd")
    private String spd;

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getSpd() {
        return spd;
    }

    public void setSpd(String spd) {
        this.spd = spd;
    }
}
