package com.example.jessyuan.alldemo.model.weather;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by JessYuan on 10/03/2017.
 */
public class UpdateBean extends RealmObject {
    /**
     * loc : 2016-12-27 15:52
     * utc : 2016-12-27 07:52
     */

    @SerializedName("loc")
    private String loc;
    @SerializedName("utc")
    private String utc;

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }
}
