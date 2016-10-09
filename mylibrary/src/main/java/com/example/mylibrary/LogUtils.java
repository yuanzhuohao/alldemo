package com.example.mylibrary;

import android.util.Log;

/**
 * Created by Jess Yuan on 23/09/2016.
 */

public class LogUtils {

    private static boolean isOn = true;

    public static void init(boolean turnOn) {
        isOn = turnOn;
    }

    public static void d(String tag, String msg) {
        if (isOn) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isOn) {
            Log.e(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isOn) {
            Log.v(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isOn) {
            Log.w(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isOn) {
            Log.i(tag, msg);
        }
    }

}
