package com.example.jessyuan.alldemo.model.weather;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by JessYuan on 10/03/2017.
 */
public class BasicBean extends RealmObject {
    /**
     * city : 东莞
     * cnty : 中国
     * id : CN101281601
     * lat : 23.047000
     * lon : 113.736000
     * update : {"loc":"2016-12-27 15:52","utc":"2016-12-27 07:52"}
     */

    @SerializedName("city")
    private String city;
    @SerializedName("id")
    private String id;
    @SerializedName("update")
    private UpdateBean update;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UpdateBean getUpdate() {
        return update;
    }

    public void setUpdate(UpdateBean update) {
        this.update = update;
    }

}
