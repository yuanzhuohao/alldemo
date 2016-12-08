package com.example.jessyuan.alldemo.helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.support.annotation.NonNull;

import com.example.mylibrary.LogUtils;

/**
 * Created by JessYuan on 08/12/2016.
 */

public class UserManager {

    private static final String TAG = "UserManager";

    private static UserManager instance;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private FirebaseUser mUser;

    /**
     * get UserManager instance
     * @return UserManager
     */
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }

        return instance;
    }

    /**
     * it was called by it itself with private
     */
    private UserManager() {
        mAuth = FirebaseAuth.getInstance();
        setAuthStateListener();
    }

    public FirebaseAuth getAuth() {
        return mAuth;
    }

    public void addAuthStateListener() {
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    public void removeAuthStateListener() {
        if (mAuthStateListener != null) {
            getAuth().removeAuthStateListener(mAuthStateListener);
        }
    }

    public FirebaseUser getUser() {
        return mUser;
    }

    private void setAuthStateListener() {
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mUser = firebaseAuth.getCurrentUser();

                if (mUser != null) {
                    LogUtils.d(TAG, "signed in: " + mUser.getUid());
                } else {
                    LogUtils.d(TAG, "signed out: ");
                }
            }
        };
    }

}
