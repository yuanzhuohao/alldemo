package com.example.jessyuan.alldemo.model;

import com.google.gson.annotations.SerializedName;

import com.example.jessyuan.alldemo.model.weather.AqiBean;
import com.example.jessyuan.alldemo.model.weather.BasicBean;
import com.example.jessyuan.alldemo.model.weather.DailyForecastBean;
import com.example.jessyuan.alldemo.model.weather.HourlyForecastBean;
import com.example.jessyuan.alldemo.model.weather.NowBean;
import com.example.jessyuan.alldemo.model.weather.SuggestionBean;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by JessYuan on 27/12/2016.
 */

public class Weather extends RealmObject {

    /**
     * alarms : [{"level":"黄色","stat":"预警中","title":"广东省东莞市气象台发布寒冷黄色预警","txt":"东莞市气象局于12月26日16时00分发布寒冷黄色预警信号，请注意防御。","type":"寒冷"}]
     * aqi : {"city":{"aqi":"26","co":"1","no2":"21","o3":"55","pm10":"25","pm25":"9","qlty":"优","so2":"7"}}
     * basic : {"city":"东莞","cnty":"中国","id":"CN101281601","lat":"23.047000","lon":"113.736000","update":{"loc":"2016-12-27 15:52","utc":"2016-12-27 07:52"}}
     * daily_forecast : [{"astro":{"sr":"07:04","ss":"17:48"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2016-12-27","hum":"64","pcpn":"0.0","pop":"0","pres":"1023","tmp":{"max":"17","min":"8"},"uv":"7","vis":"10","wind":{"deg":"21","dir":"北风","sc":"3-4","spd":"15"}},{"astro":{"sr":"07:04","ss":"17:48"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2016-12-28","hum":"58","pcpn":"0.0","pop":"0","pres":"1024","tmp":{"max":"15","min":"9"},"uv":"2","vis":"10","wind":{"deg":"24","dir":"北风","sc":"3-4","spd":"16"}},{"astro":{"sr":"07:05","ss":"17:49"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-12-29","hum":"50","pcpn":"0.0","pop":"1","pres":"1026","tmp":{"max":"16","min":"10"},"uv":"6","vis":"10","wind":{"deg":"21","dir":"北风","sc":"3-4","spd":"12"}},{"astro":{"sr":"07:05","ss":"17:49"},"cond":{"code_d":"104","code_n":"101","txt_d":"阴","txt_n":"多云"},"date":"2016-12-30","hum":"54","pcpn":"0.0","pop":"0","pres":"1025","tmp":{"max":"17","min":"13"},"uv":"6","vis":"10","wind":{"deg":"38","dir":"无持续风向","sc":"微风","spd":"1"}},{"astro":{"sr":"07:05","ss":"17:50"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2016-12-31","hum":"54","pcpn":"0.0","pop":"0","pres":"1022","tmp":{"max":"18","min":"14"},"uv":"6","vis":"10","wind":{"deg":"53","dir":"无持续风向","sc":"微风","spd":"9"}},{"astro":{"sr":"07:06","ss":"17:51"},"cond":{"code_d":"305","code_n":"104","txt_d":"小雨","txt_n":"阴"},"date":"2017-01-01","hum":"58","pcpn":"0.0","pop":"2","pres":"1021","tmp":{"max":"19","min":"17"},"uv":"-999","vis":"10","wind":{"deg":"47","dir":"无持续风向","sc":"微风","spd":"3"}},{"astro":{"sr":"07:06","ss":"17:51"},"cond":{"code_d":"104","code_n":"101","txt_d":"阴","txt_n":"多云"},"date":"2017-01-02","hum":"60","pcpn":"0.0","pop":"2","pres":"1020","tmp":{"max":"22","min":"16"},"uv":"-999","vis":"10","wind":{"deg":"54","dir":"无持续风向","sc":"微风","spd":"5"}},{"astro":{"sr":"07:07","ss":"17:53"},"cond":{"code_d":"103","code_n":"104","txt_d":"晴间多云","txt_n":"阴"},"date":"2017-01-03","hum":"68","pcpn":"0.0","pop":"0","pres":"1019","tmp":{"max":"29","min":"17"},"uv":"-999","vis":"10","wind":{"deg":"31","dir":"东北风","sc":"4-5","spd":"39"}},{"astro":{"sr":"07:07","ss":"17:53"},"cond":{"code_d":"103","code_n":"104","txt_d":"晴间多云","txt_n":"阴"},"date":"2017-01-04","hum":"65","pcpn":"0.0","pop":"0","pres":"1019","tmp":{"max":"26","min":"18"},"uv":"-999","vis":"10","wind":{"deg":"71","dir":"东北风","sc":"4-5","spd":"39"}},{"astro":{"sr":"07:07","ss":"17:54"},"cond":{"code_d":"104","code_n":"103","txt_d":"阴","txt_n":"晴间多云"},"date":"2017-01-05","hum":"73","pcpn":"0.2","pop":"10","pres":"1016","tmp":{"max":"26","min":"21"},"uv":"-999","vis":"10","wind":{"deg":"149","dir":"东南风","sc":"4-5","spd":"39"}}]
     * hourly_forecast : [{"date":"2016-12-27 16:00","hum":"53","pop":"0","pres":"1022","tmp":"18","wind":{"deg":"25","dir":"东北风","sc":"3-4","spd":"24"}},{"date":"2016-12-27 17:00","hum":"54","pop":"0","pres":"1022","tmp":"17","wind":{"deg":"29","dir":"东北风","sc":"3-4","spd":"23"}},{"date":"2016-12-27 18:00","hum":"57","pop":"0","pres":"1022","tmp":"16","wind":{"deg":"32","dir":"东北风","sc":"3-4","spd":"20"}},{"date":"2016-12-27 19:00","hum":"60","pop":"0","pres":"1022","tmp":"14","wind":{"deg":"35","dir":"东北风","sc":"3-4","spd":"17"}},{"date":"2016-12-27 20:00","hum":"63","pop":"0","pres":"1023","tmp":"13","wind":{"deg":"38","dir":"东北风","sc":"微风","spd":"14"}},{"date":"2016-12-27 21:00","hum":"64","pop":"0","pres":"1023","tmp":"12","wind":{"deg":"35","dir":"东北风","sc":"微风","spd":"13"}},{"date":"2016-12-27 22:00","hum":"65","pop":"0","pres":"1024","tmp":"12","wind":{"deg":"32","dir":"东北风","sc":"微风","spd":"12"}},{"date":"2016-12-27 23:00","hum":"66","pop":"0","pres":"1025","tmp":"11","wind":{"deg":"29","dir":"东北风","sc":"微风","spd":"10"}},{"date":"2016-12-28 00:00","hum":"67","pop":"0","pres":"1025","tmp":"11","wind":{"deg":"27","dir":"东北风","sc":"微风","spd":"10"}},{"date":"2016-12-28 01:00","hum":"68","pop":"0","pres":"1025","tmp":"11","wind":{"deg":"24","dir":"东北风","sc":"微风","spd":"10"}},{"date":"2016-12-28 02:00","hum":"68","pop":"0","pres":"1025","tmp":"11","wind":{"deg":"22","dir":"东北风","sc":"微风","spd":"9"}},{"date":"2016-12-28 03:00","hum":"68","pop":"0","pres":"1025","tmp":"10","wind":{"deg":"20","dir":"东北风","sc":"微风","spd":"10"}},{"date":"2016-12-28 04:00","hum":"68","pop":"0","pres":"1024","tmp":"10","wind":{"deg":"17","dir":"东北风","sc":"微风","spd":"11"}},{"date":"2016-12-28 05:00","hum":"68","pop":"0","pres":"1024","tmp":"9","wind":{"deg":"15","dir":"东北风","sc":"微风","spd":"12"}},{"date":"2016-12-28 06:00","hum":"67","pop":"0","pres":"1024","tmp":"10","wind":{"deg":"20","dir":"东北风","sc":"微风","spd":"11"}},{"date":"2016-12-28 07:00","hum":"65","pop":"0","pres":"1025","tmp":"10","wind":{"deg":"24","dir":"东北风","sc":"微风","spd":"10"}},{"date":"2016-12-28 08:00","hum":"64","pop":"0","pres":"1025","tmp":"10","wind":{"deg":"28","dir":"东北风","sc":"微风","spd":"10"}},{"date":"2016-12-28 09:00","hum":"59","pop":"0","pres":"1025","tmp":"12","wind":{"deg":"32","dir":"东北风","sc":"微风","spd":"10"}},{"date":"2016-12-28 10:00","hum":"55","pop":"0","pres":"1025","tmp":"13","wind":{"deg":"37","dir":"东北风","sc":"微风","spd":"11"}},{"date":"2016-12-28 11:00","hum":"50","pop":"0","pres":"1026","tmp":"15","wind":{"deg":"41","dir":"东北风","sc":"微风","spd":"12"}},{"date":"2016-12-28 12:00","hum":"52","pop":"0","pres":"1025","tmp":"15","wind":{"deg":"36","dir":"东北风","sc":"微风","spd":"10"}},{"date":"2016-12-28 13:00","hum":"54","pop":"0","pres":"1024","tmp":"16","wind":{"deg":"31","dir":"东北风","sc":"微风","spd":"9"}},{"date":"2016-12-28 14:00","hum":"56","pop":"0","pres":"1023","tmp":"16","wind":{"deg":"26","dir":"东北风","sc":"微风","spd":"7"}},{"date":"2016-12-28 15:00","hum":"57","pop":"0","pres":"1023","tmp":"17","wind":{"deg":"34","dir":"东北风","sc":"微风","spd":"7"}}]
     * now : {"cond":{"code":"101","txt":"多云"},"fl":"17","hum":"52","pcpn":"0","pres":"1020","tmp":"15","vis":"10","wind":{"deg":"60","dir":"东北风","sc":"6-7","spd":"37"}}
     * status : ok
     * suggestion : {"air":{"brf":"良","txt":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"},"comf":{"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},"flu":{"brf":"极易发","txt":"将有一次强降温过程，天气寒冷，极易发生感冒，请特别注意增加衣服保暖防寒。"},"sport":{"brf":"较适宜","txt":"天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意避风保暖。"},"trav":{"brf":"适宜","txt":"天气较好，风稍大，但温度适宜，是个好天气哦。适宜旅游，您可以尽情地享受大自然的无限风光。"},"uv":{"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}}
     */

    @SerializedName("aqi")
    private AqiBean aqi;
    @SerializedName("basic")
    private BasicBean basic;
    @SerializedName("now")
    private NowBean now;
    @SerializedName("suggestion")
    private SuggestionBean suggestion;
    @SerializedName("daily_forecast")
    private RealmList<DailyForecastBean> dailyForecast;
    @SerializedName("hourly_forecast")
    private RealmList<HourlyForecastBean> hourlyForecast;

    public AqiBean getAqi() {
        return aqi;
    }

    public void setAqi(AqiBean aqi) {
        this.aqi = aqi;
    }

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public NowBean getNow() {
        return now;
    }

    public void setNow(NowBean now) {
        this.now = now;
    }

    public SuggestionBean getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(SuggestionBean suggestion) {
        this.suggestion = suggestion;
    }

    public RealmList<DailyForecastBean> getDailyForecast() {
        return dailyForecast;
    }

    public void setDailyForecast(List<DailyForecastBean> dailyForecast) {
        this.dailyForecast = (RealmList<DailyForecastBean>) dailyForecast;
    }

    public RealmList<HourlyForecastBean> getHourlyForecast() {
        return hourlyForecast;
    }

    public void setHourlyForecast(List<HourlyForecastBean> hourlyForecast) {
        this.hourlyForecast = (RealmList<HourlyForecastBean>) hourlyForecast;
    }

}
