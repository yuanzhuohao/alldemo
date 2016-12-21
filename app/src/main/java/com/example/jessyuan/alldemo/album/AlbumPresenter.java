package com.example.jessyuan.alldemo.album;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.jessyuan.alldemo.helper.ImageLoader;
import com.example.jessyuan.alldemo.listeners.ImageLoaderListener;
import com.example.jessyuan.alldemo.model.Folder;
import com.example.jessyuan.alldemo.model.Image;
import com.example.mylibrary.LogUtils;
import com.example.mylibrary.ToastUtils;

import org.reactivestreams.Subscriber;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by JessYuan on 12/12/2016.
 */

public class AlbumPresenter implements AlbumContract.AlbumPresenter {

    private List<Folder> mFolderList;
    private List<Image> mImageList;

    private AlbumContract.AlbumView mView;
    private Context mContext;

    private boolean isEdit = false;

    private Folder currentFolder;

    @Inject
    ImageLoader mImageLoader;

    @Inject
    public AlbumPresenter(Context context, AlbumContract.AlbumView view) {
        mContext = context;
        mView = view;
    }

    /**
     * Load Device Images from external storage
     */
    @Override
    public void loadDeviceImages(boolean refresh) {
        SingleObserver<List<Folder>> singleObserver = new SingleObserver<List<Folder>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<Folder> folders) {
                mFolderList = folders;
                mView.showFolders(mFolderList);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("load images failure", e.getMessage());
            }
        };

        if (refresh) {
            mImageLoader.refreshImages()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(singleObserver);
        } else {
            mImageLoader.loadImages()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(singleObserver);
            ;
        }
    }

    @Override
    public void openFolder(Folder folder) {
        currentFolder = folder;
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

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;

        // remove all selected images
        if (isEdit()) {
            for (Image image : mImageList) {
                image.setSelected(false);
            }
        }

        mView.showImageEditOrNormalMode();
    }

    @Override
    public void selectImage(int position) {
        if (position >= 0 && position < mImageList.size()) {
            Image image =  mImageList.get(position);
            image.setSelected(!image.isSelected());
        }

        mView.showChangeImage(position);
    }

    @Override
    public void deleteSelectedImage() {
        boolean success = true;
        ArrayList<Image> selectedImages = new ArrayList<>();

        for (Image image : mImageList) {
            if (image.isSelected()) {
                File file = new File(image.getPath());
                success = file.delete();
                if (!success) {
                    ToastUtils.makeTextShort(mContext, image.getName() + "delete failure");
                } else {
                    selectedImages.add(image);
                }
            }
        }

        for (Image selectedImage : selectedImages) {
            mImageList.remove(selectedImage);
        }

        if (success) {
            ToastUtils.makeTextShort(mContext, "delete successful");
        }

        mView.showImages(currentFolder, mImageList);
    }
}
