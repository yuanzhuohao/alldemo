package com.example.permissionmanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by JessYuan on 29/11/2016.
 */

public class PermissionRationaleDialogFragment extends DialogFragment {

    private static final String ARG_PERMISSION = "PERMISSION";
    private static final String ARG_RATIONALE_MSG = "RATIONALE_MSG";

    private String permission;

    public static PermissionRationaleDialogFragment newInstance(String permission, String rationaleMsg) {

        Bundle args = new Bundle();

        PermissionRationaleDialogFragment fragment = new PermissionRationaleDialogFragment();
        args.putString(ARG_PERMISSION, permission);
        args.putString(ARG_RATIONALE_MSG, rationaleMsg);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        permission = getArguments().getString(ARG_PERMISSION);
        String rationaleMsg = getArguments().getString(ARG_RATIONALE_MSG);

        return new AlertDialog.Builder(getActivity())
                .setMessage(rationaleMsg)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                }).create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (getActivity() != null && getActivity() instanceof DialogDismissedListener) {
            ((DialogDismissedListener)getActivity()).onPermissionRationaleDialogDismiss(permission);
        }
    }


    /**
     * This is called when dialog dismiss by delegate activity
     */
    public interface DialogDismissedListener {
        void onPermissionRationaleDialogDismiss(String permission);
    }
}
