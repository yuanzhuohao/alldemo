package com.example.jessyuan.alldemo.camera;

import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.example.jessyuan.alldemo.model.Image;
import com.example.mylibrary.FileUtils;
import com.example.mylibrary.LogUtils;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by JessYuan on 30/11/2016.
 */

public class DefaultCameraModule implements CameraModule {

    String filePath;
    String fileName;

    @Inject
    public DefaultCameraModule() {
    }

    public static final int REQUEST_IMAGE_CAPTURE = 0x000001;

    @Override
    public Intent getCameraIntent(Context context) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imageFile = null;
        try {
             imageFile = FileUtils.createImageFile("AllDemo");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (imageFile != null) {

            String fileProviderName = context.getApplicationContext().getPackageName() + ".fileprovider";
            Uri uri = FileProvider.getUriForFile(context.getApplicationContext(),
                    fileProviderName,
                    imageFile);

            filePath = imageFile.getAbsolutePath();
            fileName = imageFile.getName();

            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

            return intent;
        }

        return null;
    }

    @Override
    public void getImage(final Context context, Intent intent, final ImageCaptureReadyListener listener) {
        final Uri imageUri = Uri.parse("file:" + filePath);

        if (imageUri != null) {
            // It will read metadata from file or add file to media content provider
            MediaScannerConnection.scanFile(context.getApplicationContext(),
                    new String[]{imageUri.getPath()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            LogUtils.i("ImageFile Scanner", "File " + path
                                + " was scanned successfully: " + uri);

                            context.revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                             | Intent.FLAG_GRANT_READ_URI_PERMISSION);

                            listener.onImageReady(new Image(0, fileName, filePath, false));
                        }
                    });
        }
    }
}
