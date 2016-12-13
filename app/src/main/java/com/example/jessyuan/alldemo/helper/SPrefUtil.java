package com.example.jessyuan.alldemo.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by JessYuan on 13/12/2016.
 */

public class SPrefUtil {

    private static final String PREFS_NAME = "AllDemo_Prefs";

    public static void put(Context context, String key, Object value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (value instanceof String) {
            editor.putString(key, (String)value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (int) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (long) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (float)value);
        } else if (value instanceof Set) {
            editor.putStringSet(key, (Set<String>) value);
        }

        editor.commit();
    }

    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        return sharedPreferences.getString(key, defValue);
    }

    public static Boolean getBoolean(Context context, String key, Boolean defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        return sharedPreferences.getBoolean(key, defValue);
    }

    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        return sharedPreferences.getInt(key, defValue);
    }

    public static long getLong(Context context, String key, long defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        return sharedPreferences.getLong(key, defValue);
    }

    public static float getFloat(Context context, String key, float defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        return sharedPreferences.getFloat(key, defValue);
    }

}
