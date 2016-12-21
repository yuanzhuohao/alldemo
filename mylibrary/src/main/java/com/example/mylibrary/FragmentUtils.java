package com.example.mylibrary;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Jess Yuan on 23/09/2016.
 */

public class FragmentUtils {

    public static void addFragmentToActivity(FragmentManager fm, Fragment f, int containerId) {
        String tag = f.getClass().getName();
        fm.beginTransaction()
                .add(containerId, f, tag)
                .commit();
    }

    public static void replaceFragment(FragmentManager fm, Fragment f, int containerId) {
        String tag = f.getClass().getName();
        fm.beginTransaction()
          .addToBackStack(tag)
          .replace(containerId, f, tag)
          .commit();
    }


}
