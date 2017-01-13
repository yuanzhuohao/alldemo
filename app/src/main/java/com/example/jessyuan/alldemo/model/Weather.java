package com.example.jessyuan.alldemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by JessYuan on 27/12/2016.
 */

public class Weather {

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
    @SerializedName("status")
    private String status;
    @SerializedName("suggestion")
    private SuggestionBean suggestion;
    @SerializedName("alarms")
    private List<AlarmsBean> alarms;
    @SerializedName("daily_forecast")
    private List<DailyForecastBean> dailyForecast;
    @SerializedName("hourly_forecast")
    private List<HourlyForecastBean> hourlyForecast;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SuggestionBean getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(SuggestionBean suggestion) {
        this.suggestion = suggestion;
    }

    public List<AlarmsBean> getAlarms() {
        return alarms;
    }

    public void setAlarms(List<AlarmsBean> alarms) {
        this.alarms = alarms;
    }

    public List<DailyForecastBean> getDailyForecast() {
        return dailyForecast;
    }

    public void setDailyForecast(List<DailyForecastBean> dailyForecast) {
        this.dailyForecast = dailyForecast;
    }

    public List<HourlyForecastBean> getHourlyForecast() {
        return hourlyForecast;
    }

    public void setHourlyForecast(List<HourlyForecastBean> hourlyForecast) {
        this.hourlyForecast = hourlyForecast;
    }

    public static class AqiBean  {
        /**
         * city : {"aqi":"26","co":"1","no2":"21","o3":"55","pm10":"25","pm25":"9","qlty":"优","so2":"7"}
         */

        @SerializedName("city")
        private CityBean city;

        public CityBean getCity() {
            return city;
        }

        public void setCity(CityBean city) {
            this.city = city;
        }

        public static class CityBean  {
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
            @SerializedName("co")
            private String co;
            @SerializedName("no2")
            private String no2;
            @SerializedName("o3")
            private String o3;
            @SerializedName("pm10")
            private String pm10;
            @SerializedName("pm25")
            private String pm25;
            @SerializedName("qlty")
            private String qlty;
            @SerializedName("so2")
            private String so2;

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }

            public String getCo() {
                return co;
            }

            public void setCo(String co) {
                this.co = co;
            }

            public String getNo2() {
                return no2;
            }

            public void setNo2(String no2) {
                this.no2 = no2;
            }

            public String getO3() {
                return o3;
            }

            public void setO3(String o3) {
                this.o3 = o3;
            }

            public String getPm10() {
                return pm10;
            }

            public void setPm10(String pm10) {
                this.pm10 = pm10;
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

            public String getSo2() {
                return so2;
            }

            public void setSo2(String so2) {
                this.so2 = so2;
            }
        }
    }

    public static class BasicBean  {
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
        @SerializedName("cnty")
        private String cnty;
        @SerializedName("id")
        private String id;
        @SerializedName("lat")
        private String lat;
        @SerializedName("lon")
        private String lon;
        @SerializedName("update")
        private UpdateBean update;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCnty() {
            return cnty;
        }

        public void setCnty(String cnty) {
            this.cnty = cnty;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
        }

        public static class UpdateBean  {
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
    }

    public static class NowBean  {
        /**
         * cond : {"code":"101","txt":"多云"}
         * fl : 17
         * hum : 52
         * pcpn : 0
         * pres : 1020
         * tmp : 15
         * vis : 10
         * wind : {"deg":"60","dir":"东北风","sc":"6-7","spd":"37"}
         */

        @SerializedName("cond")
        private CondBean cond;
        @SerializedName("fl")
        private String fl;
        @SerializedName("hum")
        private String hum;
        @SerializedName("pcpn")
        private String pcpn;
        @SerializedName("pres")
        private String pres;
        @SerializedName("tmp")
        private String tmp;
        @SerializedName("vis")
        private String vis;
        @SerializedName("wind")
        private WindBean wind;

        public CondBean getCond() {
            return cond;
        }

        public void setCond(CondBean cond) {
            this.cond = cond;
        }

        public String getFl() {
            return fl;
        }

        public void setFl(String fl) {
            this.fl = fl;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPcpn() {
            return pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
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

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public WindBean getWind() {
            return wind;
        }

        public void setWind(WindBean wind) {
            this.wind = wind;
        }

        public static class CondBean  {
            /**
             * code : 101
             * txt : 多云
             */

            @SerializedName("code")
            private String code;
            @SerializedName("txt")
            private String txt;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class WindBean  {
            /**
             * deg : 60
             * dir : 东北风
             * sc : 6-7
             * spd : 37
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
    }

    public static class SuggestionBean  {
        /**
         * air : {"brf":"良","txt":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"}
         * comf : {"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"}
         * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
         * drsg : {"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"}
         * flu : {"brf":"极易发","txt":"将有一次强降温过程，天气寒冷，极易发生感冒，请特别注意增加衣服保暖防寒。"}
         * sport : {"brf":"较适宜","txt":"天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意避风保暖。"}
         * trav : {"brf":"适宜","txt":"天气较好，风稍大，但温度适宜，是个好天气哦。适宜旅游，您可以尽情地享受大自然的无限风光。"}
         * uv : {"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}
         */

        @SerializedName("air")
        private AirBean air;
        @SerializedName("comf")
        private ComfBean comf;
        @SerializedName("cw")
        private CwBean cw;
        @SerializedName("drsg")
        private DrsgBean drsg;
        @SerializedName("flu")
        private FluBean flu;
        @SerializedName("sport")
        private SportBean sport;
        @SerializedName("trav")
        private TravBean trav;
        @SerializedName("uv")
        private UvBean uv;

        public AirBean getAir() {
            return air;
        }

        public void setAir(AirBean air) {
            this.air = air;
        }

        public ComfBean getComf() {
            return comf;
        }

        public void setComf(ComfBean comf) {
            this.comf = comf;
        }

        public CwBean getCw() {
            return cw;
        }

        public void setCw(CwBean cw) {
            this.cw = cw;
        }

        public DrsgBean getDrsg() {
            return drsg;
        }

        public void setDrsg(DrsgBean drsg) {
            this.drsg = drsg;
        }

        public FluBean getFlu() {
            return flu;
        }

        public void setFlu(FluBean flu) {
            this.flu = flu;
        }

        public SportBean getSport() {
            return sport;
        }

        public void setSport(SportBean sport) {
            this.sport = sport;
        }

        public TravBean getTrav() {
            return trav;
        }

        public void setTrav(TravBean trav) {
            this.trav = trav;
        }

        public UvBean getUv() {
            return uv;
        }

        public void setUv(UvBean uv) {
            this.uv = uv;
        }

        public static class AirBean  {
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

        public static class ComfBean  {
            /**
             * brf : 舒适
             * txt : 白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。
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

        public static class CwBean  {
            /**
             * brf : 较适宜
             * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
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

        public static class DrsgBean  {
            /**
             * brf : 较冷
             * txt : 建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
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

        public static class FluBean  {
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

        public static class SportBean  {
            /**
             * brf : 较适宜
             * txt : 天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意避风保暖。
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

        public static class TravBean  {
            /**
             * brf : 适宜
             * txt : 天气较好，风稍大，但温度适宜，是个好天气哦。适宜旅游，您可以尽情地享受大自然的无限风光。
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

        public static class UvBean  {
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
    }

    public static class AlarmsBean  {
        /**
         * level : 黄色
         * stat : 预警中
         * title : 广东省东莞市气象台发布寒冷黄色预警
         * txt : 东莞市气象局于12月26日16时00分发布寒冷黄色预警信号，请注意防御。
         * type : 寒冷
         */

        @SerializedName("level")
        private String level;
        @SerializedName("stat")
        private String stat;
        @SerializedName("title")
        private String title;
        @SerializedName("txt")
        private String txt;
        @SerializedName("type")
        private String type;

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class DailyForecastBean  {
        /**
         * astro : {"sr":"07:04","ss":"17:48"}
         * cond : {"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"}
         * date : 2016-12-27
         * hum : 64
         * pcpn : 0.0
         * pop : 0
         * pres : 1023
         * tmp : {"max":"17","min":"8"}
         * uv : 7
         * vis : 10
         * wind : {"deg":"21","dir":"北风","sc":"3-4","spd":"15"}
         */

        @SerializedName("astro")
        private AstroBean astro;
        @SerializedName("cond")
        private CondBeanX cond;
        @SerializedName("date")
        private String date;
        @SerializedName("hum")
        private String hum;
        @SerializedName("pcpn")
        private String pcpn;
        @SerializedName("pop")
        private String pop;
        @SerializedName("pres")
        private String pres;
        @SerializedName("tmp")
        private TmpBean tmp;
        @SerializedName("uv")
        private String uv;
        @SerializedName("vis")
        private String vis;
        @SerializedName("wind")
        private WindBeanX wind;

        public AstroBean getAstro() {
            return astro;
        }

        public void setAstro(AstroBean astro) {
            this.astro = astro;
        }

        public CondBeanX getCond() {
            return cond;
        }

        public void setCond(CondBeanX cond) {
            this.cond = cond;
        }

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

        public String getPcpn() {
            return pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
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

        public TmpBean getTmp() {
            return tmp;
        }

        public void setTmp(TmpBean tmp) {
            this.tmp = tmp;
        }

        public String getUv() {
            return uv;
        }

        public void setUv(String uv) {
            this.uv = uv;
        }

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public WindBeanX getWind() {
            return wind;
        }

        public void setWind(WindBeanX wind) {
            this.wind = wind;
        }

        public static class AstroBean  {
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

        public static class CondBeanX  {
            /**
             * code_d : 100
             * code_n : 100
             * txt_d : 晴
             * txt_n : 晴
             */

            @SerializedName("code_d")
            private String codeD;
            @SerializedName("code_n")
            private String codeN;
            @SerializedName("txt_d")
            private String txtD;
            @SerializedName("txt_n")
            private String txtN;

            public String getCodeD() {
                return codeD;
            }

            public void setCodeD(String codeD) {
                this.codeD = codeD;
            }

            public String getCodeN() {
                return codeN;
            }

            public void setCodeN(String codeN) {
                this.codeN = codeN;
            }

            public String getTxtD() {
                return txtD;
            }

            public void setTxtD(String txtD) {
                this.txtD = txtD;
            }

            public String getTxtN() {
                return txtN;
            }

            public void setTxtN(String txtN) {
                this.txtN = txtN;
            }
        }

        public static class TmpBean  {
            /**
             * max : 17
             * min : 8
             */

            @SerializedName("max")
            private String max;
            @SerializedName("min")
            private String min;

            public String getMax() {
                return max;
            }

            public void setMax(String max) {
                this.max = max;
            }

            public String getMin() {
                return min;
            }

            public void setMin(String min) {
                this.min = min;
            }
        }

        public static class WindBeanX  {
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
    }

    public static class HourlyForecastBean  {
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

        public static class WindBeanXX  {
            /**
             * deg : 25
             * dir : 东北风
             * sc : 3-4
             * spd : 24
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
    }
}
