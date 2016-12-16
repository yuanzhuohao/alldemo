package com.example.jessyuan.alldemo.model;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by JessYuan on 25/11/2016.
 */

public class Folder {

    private String mId;
    private String folderName;
    private List<Image> images;

    public Folder(String folderName) {
        this(UUID.randomUUID().toString(), folderName);
    }

    public Folder(@NonNull String id, String folderName) {
        this.mId = id;
        this.folderName = folderName;
        images = new ArrayList<>();
    }

    public String getId() {
        return mId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
