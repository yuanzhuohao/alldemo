package com.example.mylibrary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by JessYuan on 29/12/2016.
 */

public class DateUtils {
    public static String getWeek(String dateString, String pattern) {
        String[] weeks = {"星期日", "星期一", "星期二", "星期三","星期四","星期五","星期六"};
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();

        if (dateString != "") {
            try {
                date = dateFormat.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (index < 0) {
            index = 0;
        }

        return weeks[index];
    }

    public static String getWeek(Date date) {
        String[] weeks = {"星期日", "星期一", "星期二", "星期三","星期四","星期五","星期六"};

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (index < 0) {
            index = 0;
        }

        return weeks[index];
    }
}
