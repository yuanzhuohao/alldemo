package com.example.mylibrary;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jess Yuan on 21/10/2016.
 */

public class FileUtils {

    public static File createImageFile(Context context) throws IOException {
        String tempString = new SimpleDateFormat("yyyyMMddHH_mmss").format(new Date());
        String filename = "JPEG_" + tempString;
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File file = File.createTempFile(filename, ".jpg", storageDir);

        return file;
    }

    public static File createOtherFileToAppDataDir(Context context, String suffix) throws IOException {
        String tempString = new SimpleDateFormat("yyyyMMddHH_mmss").format(new Date());
        String filename = tempString;
        File storageDir = context.getExternalFilesDir("Data");
        File file = File.createTempFile(filename, suffix, storageDir);

        return file;
    }

    public static File createOtherFileToDataDir(String suffix, String appName) throws IOException {
        String tempString = new SimpleDateFormat("yyyyMMddHH_mmss").format(new Date());
        String filename = tempString;
        File storageDir = new File(Environment.getExternalStorageDirectory().getPath() + "/" + appName);
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
        File file = File.createTempFile(filename, suffix, storageDir);

        return file;
    }

}
