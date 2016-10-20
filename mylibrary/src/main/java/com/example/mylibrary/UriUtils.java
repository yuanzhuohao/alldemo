package com.example.mylibrary;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by Jess Yuan on 19/10/2016.
 */

public class UriUtils {

    public static String parseToPath(Context context, Uri uri) {
        String path = "";
        if (uri != null && "content".equals(uri.getScheme())) {
            String[] FILE = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(uri,
                    FILE, null, null, null);

            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(FILE[0]);
            path = cursor.getString(columnIndex);
            cursor.close();
        } else {
            path = uri.getPath();
        }

        return path;
    }

}
