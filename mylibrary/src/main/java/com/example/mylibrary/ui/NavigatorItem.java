package com.example.mylibrary.ui;

/**
 * Created by JessYuan on 30/12/2016.
 */

public class NavigatorItem {

    private String label;
    private int iconResId;
    private int number = 0;
    private boolean notify;
    private int mPosition = -1;
    private boolean mChecked = false;

    public NavigatorItem(int iconResId) {
        this("", iconResId);
    }

    public NavigatorItem(String label, int iconResId) {
        this(label, iconResId, false);
    }

    public NavigatorItem(String label, int iconResId, boolean notify) {
        this.label = label;
        this.iconResId = iconResId;
        this.notify = notify;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    public boolean isChecked() {
        return mChecked;
    }

    public void setChecked(boolean checked) {
        mChecked = checked;
    }
}
