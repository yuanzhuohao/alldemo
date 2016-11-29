package com.example.jessyuan.alldemo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JessYuan on 25/11/2016.
 */

public class Image implements Parcelable {
    private long id;
    private String name;
    private String path;
    private boolean selected;

    public Image(long id, String name, String path, boolean selected) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.selected = selected;
    }

    protected Image(Parcel in) {
        id = in.readLong();
        name = in.readString();
        path = in.readString();
        selected = in.readByte() != 0;
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(path);
        dest.writeByte((byte) (selected ? 1 : 0));
    }
}
