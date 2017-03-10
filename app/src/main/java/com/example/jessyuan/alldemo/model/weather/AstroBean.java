package com.example.jessyuan.alldemo.model.weather;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by JessYuan on 10/03/2017.
 */
public class AstroBean extends RealmObject {
    /**
     * sr : 07:04
     * ss : 17:48
     */

    @SerializedName("sr")
    private String sr;
    @SerializedName("ss")
    private String ss;

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }
}
