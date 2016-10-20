package com.example.mylibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

/**
 * Created by Jess Yuan on 19/10/2016.
 */

public class BitmapUtils {

    public static Bitmap scale(View view, String path) {
        int targetH = view.getHeight();
        int targetW = view.getWidth();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int photoW = options.outWidth;
        int photoH = options.outHeight;

        int scaleFactor = Math.min(photoH/targetH, photoW/targetW);

        options.inJustDecodeBounds = false;
        options.inSampleSize = scaleFactor;
        options.inPurgeable = true;

        return BitmapFactory.decodeFile(path, options);
    }

}
