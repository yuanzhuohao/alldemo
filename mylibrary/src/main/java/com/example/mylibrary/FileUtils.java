package com.example.mylibrary;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Jess Yuan on 21/10/2016.
 */

public class FileUtils {

    private static final String TAG = "文件工具类 FileUtils";

    /**
     *  该方法创建图片文件，并把它保存在根目录的Pictures目录下。
     * @param directory
     * @return
     * @throws IOException
     */
    public static File createImageFile(String directory) throws IOException {
        String tempString = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename = "JPEG_" + tempString;
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath()
                                + "/" + directory);

        if (!storageDir.exists()) {
            if (!storageDir.mkdir()) {
                LogUtils.d(TAG, "Oops! Failed create " + directory + " directory");
                return null;
            }
        }

        File file = null;

        try {
             file = File.createTempFile(filename, ".jpg", storageDir);
        } catch (IOException ex) {
            LogUtils.d(TAG, "Oops! Failed create " + filename + " file");
        }

        return file;
    }

    /**
     * 该方法创建一个文件，后缀名自定义，保存的文件路径在App包名的路径下的Data目录下。
     * @param context
     * @param suffix
     * @return
     * @throws IOException
     */
    public static File createFileToAppDataDir(Context context, String suffix) throws IOException {
        String tempString = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename = tempString;
        File storageDir = context.getExternalFilesDir("Data");
        File file = File.createTempFile(filename, suffix, storageDir);

        return file;
    }


    /**
     * 该方法创建一个文件，后缀名自定义，保存的文件路径在外部存储路径的根目录的App名字的目录下。
     * @param suffix
     * @param appName
     * @return
     * @throws IOException
     */
    public static File createFileToDataDir(String suffix, String appName) throws IOException {
        String tempString = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename = tempString;
        File storageDir = new File(Environment.getExternalStorageDirectory().getPath() + "/" + appName);
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
        File file = File.createTempFile(filename, suffix, storageDir);

        return file;
    }

}
