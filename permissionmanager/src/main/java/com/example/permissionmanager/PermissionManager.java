package com.example.permissionmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JessYuan on 29/11/2016.
 */

public class PermissionManager {

    private static PermissionManager sPermissionManager;
    private static Map<String, Collection<WeakReference<PermissionListener>>> sPermissionRequests;

    /**
     * this method get PermissionManger, and use for itself
     */
    private static PermissionManager getInstance() {
        if (sPermissionManager == null) {
            sPermissionManager = new PermissionManager();
        }

        return sPermissionManager;
    }

    private PermissionManager() {
        sPermissionRequests = new HashMap<>();
    }

    /**
     * Request Permission delegate to another activity, and result is delivered to
     * PermissionListener
     */
    public static void askPermission(Context context, String[] permissions, String rationaleMsg, PermissionListener listener) {
        List<String> denyPermissions = new ArrayList<>();
        PermissionManager instance = getInstance();

        instance.cleanupPendingRequestList();

        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            if (sPermissionRequests.containsKey(permission)) {
                sPermissionRequests.get(permission).add(new WeakReference<PermissionListener>(listener));
                return;
            }

            sPermissionRequests.put(permission, new ArrayList<WeakReference<PermissionListener>>());
            sPermissionRequests.get(permission).add(new WeakReference<PermissionListener>(listener));

            int checkPermission = ContextCompat.checkSelfPermission(context, permission);
            if (checkPermission == PackageManager.PERMISSION_GRANTED) {
                instance.notifyListeners(permission, checkPermission);
            } else {
                denyPermissions.add(permission);
            }
        }

        if (denyPermissions.size() == 0) {
            return;
        } else {
            Intent intent = PermissionRequestDelegateActivity.newIntent(context,
                    denyPermissions.toArray(new String[denyPermissions.size()]),
                    rationaleMsg);
            context.startActivity(intent);
        }

    }

    /**
     * This is called by the DelegateActivity(PermissionRequestDelegateActivity) when it has
     * finished asking system for permission
     */
    public static void onPermissionResponse(String permission, int result) {
        PermissionManager instance = getInstance();
        instance.notifyListeners(permission, result);
    }

    private void notifyListeners(String permission, int result) {
        Collection<WeakReference<PermissionListener>> listeners = sPermissionRequests.get(permission);

        for (WeakReference<PermissionListener> listenerRer : listeners) {
            PermissionListener listener = listenerRer.get();
            if (listener != null) {
                listener.onResult(permission, result == PackageManager.PERMISSION_GRANTED);
            }
        }

        // remove all listeners for a given permission
        sPermissionRequests.remove(permission);
    }

    private void cleanupPendingRequestList() {
        for (Collection<WeakReference<PermissionListener>> listeners : sPermissionRequests.values()) {
            for (WeakReference<PermissionListener> listener : listeners) {
                if (listener.get() == null) {
                    listeners.remove(listener);
                }
            }
        }
    }

}
