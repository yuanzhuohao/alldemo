package com.example.jessyuan.alldemo.listeners;


import com.example.jessyuan.alldemo.model.Folder;
import com.example.jessyuan.alldemo.model.Image;

import java.util.List;

/**
 * Created by JessYuan on 25/11/2016.
 */

public interface ImageLoaderListener {
    void onImageLoaded(final List<Image> images, List<Folder> folders);
    void onFailed(Exception ex);
}
