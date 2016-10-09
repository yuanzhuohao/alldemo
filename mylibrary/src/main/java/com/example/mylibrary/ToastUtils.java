package com.example.mylibrary;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Jess Yuan on 23/09/2016.
 */

public class ToastUtils {

    public static void makeTextShort(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
