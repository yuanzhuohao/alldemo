package com.example.jessyuan.alldemo.camera;

import android.content.Context;
import android.content.Intent;

/**
 * It will get intent when activity call camera, and the 'getImage' method was called on onActivityResult
 * method.
 *
 * Created by JessYuan on 30/11/2016.
 */

public interface CameraModule {

    Intent getCameraIntent(Context context);

    void getImage(Context context, Intent intent, ImageCaptureReadyListener listener);

}
