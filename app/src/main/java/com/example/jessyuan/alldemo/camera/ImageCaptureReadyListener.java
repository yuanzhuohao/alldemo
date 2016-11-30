package com.example.jessyuan.alldemo.camera;

import com.example.jessyuan.alldemo.model.Image;

/**
 * Created by JessYuan on 30/11/2016.
 */

public interface ImageCaptureReadyListener {
    // it was called when image has captured and saved to storage.
    void onImageReady(Image image);
}
