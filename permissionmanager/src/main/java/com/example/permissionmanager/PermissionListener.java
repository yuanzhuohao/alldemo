package com.example.permissionmanager;

/**
 * This Listener was callback when permission response
 *
 * Created by JessYuan on 29/11/2016.
 */

public interface PermissionListener {
    void onResult(boolean permissionGranted);
}
