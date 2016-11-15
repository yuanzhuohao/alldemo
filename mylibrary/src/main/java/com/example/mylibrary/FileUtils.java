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

    /**
     *  该方法创建图片文件，并把它保存在App的包名路径目录的Pictures目录下。
     * @param context
     * @return
     * @throws IOException
     */
    public static File createImageFile(Context context) throws IOException {
        String tempString = new SimpleDateFormat("yyyyMMddHH_mmss").format(new Date());
        String filename = "JPEG_" + tempString;
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File file = File.createTempFile(filename, ".jpg", storageDir);

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
        String tempString = new SimpleDateFormat("yyyyMMddHH_mmss").format(new Date());
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
