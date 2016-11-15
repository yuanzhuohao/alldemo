package com.example.mylibrary;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by JessYuan on 14/11/2016.
 */

public class PicassoUtils {

    public static void load(Context context, int resId, ImageView imageView) {
        Picasso.with(context).load(resId).into(imageView);
    }

    public static void load(Context context, String path, ImageView imageView) {
        Picasso.with(context).load(path).into(imageView);
    }

    public static void load(Context context, File file, ImageView imageView) {
        Picasso.with(context).load(file).into(imageView);
    }

    public static void load(Context context, Uri uri, ImageView imageView) {
        Picasso.with(context).load(uri).into(imageView);
    }



}
