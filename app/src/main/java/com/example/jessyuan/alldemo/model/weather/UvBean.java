package com.example.jessyuan.alldemo.model.weather;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by JessYuan on 10/03/2017.
 */
public class UvBean extends RealmObject {
    /**
     * brf : 中等
     * txt : 属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。
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
