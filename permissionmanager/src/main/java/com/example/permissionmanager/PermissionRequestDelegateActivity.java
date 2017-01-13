package com.example.permissionmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

/**
 * Created by JessYuan on 29/11/2016.
 */

public class PermissionRequestDelegateActivity extends AppCompatActivity implements PermissionRationaleDialogFragment.DialogDismissedListener {

    private static final int REQUEST_PERMISSION_CODE = 1;

    private static final String EXTRA_PERMISSION = "EXTRA_PERMISSION";
    private static final String EXTRA_RATIONALE_MSG = "EXTRA_RATIONALE_MSG";
    private static final String TAG_PERMISSION_DIALOG_FRAGMENT = "TAG_PERMISSION_DIALOG_FRAGMENT";

    private String[] permissions;
    private String rationaleMsg;

    /**
     * Set some arguments from PermissionManager
     *
     * @param context
     * @param permissions
     * @param rationaleMsg
     * @return
     */
    public static Intent newIntent(Context context, String[] permissions, String rationaleMsg) {
        Intent intent = new Intent(context, PermissionRequestDelegateActivity.class);
        intent.putExtra(EXTRA_PERMISSION, permissions);
        intent.putExtra(EXTRA_RATIONALE_MSG, rationaleMsg);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissions = getIntent().getStringArrayExtra(EXTRA_PERMISSION);
        rationaleMsg = getIntent().getStringExtra(EXTRA_RATIONALE_MSG);

        getPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    int result = grantResults[i];

                    PermissionManager.onPermissionResponse(permission, result);
                }

                finish();
                overridePendingTransition(0,0); // disable exit animation in case when activity exit
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,0); // disable exit animation in case when user hit back button
    }

    private void getPermission() {
        boolean shouldShowRationale = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                shouldShowRationale = shouldShowRequestPermissionRationale(permission);
                if (shouldShowRationale == true) {
                    break;
                }
            }
        }

        if (shouldShowRationale && !TextUtils.isEmpty(rationaleMsg)) {
            showPermissionRationaleDialog();
        } else {
            askPermission();
        }
    }


    /**
     * show dialog that display permission rationale
     */
    private void showPermissionRationaleDialog() {
        PermissionRationaleDialogFragment fragment = PermissionRationaleDialogFragment.newInstance(permissions, rationaleMsg);
        fragment.show(getSupportFragmentManager(), TAG_PERMISSION_DIALOG_FRAGMENT);
    }

    /**
     *  Request Permission from system
     */
    private void askPermission() {
        ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onPermissionRationaleDialogDismiss(String[] pers) {
        if (pers.length == permissions.length) {
            askPermission();
        }
    }
}
