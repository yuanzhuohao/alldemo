package com.example.jessyuan.alldemo.album;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.jessyuan.alldemo.helper.ImageLoader;
import com.example.jessyuan.alldemo.listeners.ImageLoaderListener;
import com.example.jessyuan.alldemo.model.Folder;
import com.example.jessyuan.alldemo.model.Image;
import com.example.mylibrary.LogUtils;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by JessYuan on 12/12/2016.
 */

public class AlbumPresenter implements AlbumContract.AlbumPresenter {

    private List<Folder> mFolderList;
    private List<Image> mImageList;

    private AlbumContract.AlbumView mView;

    @Inject
    ImageLoader mImageLoader;

    @Inject
    public AlbumPresenter(AlbumContract.AlbumView view) {
        mView = view;
    }

    /**
     * Load Device Images from external storage
     */
    @Override
    public void loadDeviceImages(boolean refresh) {
        final Handler handler = new Handler(Looper.getMainLooper());
        ImageLoaderListener listener = new ImageLoaderListener() {
            @Override
            public void onImageLoaded(final List<Image> images, final List<Folder> folders) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mFolderList = folders;
                        mView.showFolders(mFolderList);
                    }
                });

            }

            @Override
            public void onFailed(Exception ex) {
                LogUtils.e("load images failure", ex.getMessage());
            }
        };

        if (refresh) {
            mImageLoader.refreshDeviceImages(listener);
        } else {
            mImageLoader.loadDeviceImages(listener);
        }
    }

    @Override
    public void openFolder(Folder folder) {
        mImageList = folder.getImages();
        mView.showImages(folder, mImageList);
    }

    @Override
    public void openImageViewer(int position) {
        mView.showImage(mImageList, position);
    }

    @Override
    public void start() {
        mView.showTitle("Album");
    }
}
