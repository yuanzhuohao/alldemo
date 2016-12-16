package com.example.jessyuan.alldemo.album;

import com.example.jessyuan.alldemo.base.BasePresenter;
import com.example.jessyuan.alldemo.base.BaseView;
import com.example.jessyuan.alldemo.model.Folder;
import com.example.jessyuan.alldemo.model.Image;

import java.util.List;

/**
 * Created by JessYuan on 12/12/2016.
 */

public interface AlbumContract {
    interface AlbumView extends BaseView {
        void showTitle(String title);
        void showFolders(List<Folder> list);
        void showImages(Folder folder, List<Image> list);
        void showImage(List<Image> list, int position);
    }

    interface AlbumPresenter extends BasePresenter{
        void loadDeviceImages(boolean refresh);
        void openFolder(Folder folder);
        void openImageViewer(int position);
    }
}
