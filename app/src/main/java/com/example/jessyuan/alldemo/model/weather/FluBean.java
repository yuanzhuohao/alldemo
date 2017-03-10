package com.example.jessyuan.alldemo.model.weather;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by JessYuan on 10/03/2017.
 */
public class FluBean extends RealmObject {
    /**
     * brf : 极易发
     * txt : 将有一次强降温过程，天气寒冷，极易发生感冒，请特别注意增加衣服保暖防寒。
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
