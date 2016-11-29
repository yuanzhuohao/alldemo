package com.example.jessyuan.alldemo.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by JessYuan on 25/11/2016.
 */

public class Folder {
    private String folderName;
    private List<Image> images;

    public Folder(String folderName) {
        this.folderName = folderName;
        images = new ArrayList<>();
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
