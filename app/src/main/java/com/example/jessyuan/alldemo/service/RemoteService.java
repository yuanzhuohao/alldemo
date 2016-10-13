package com.example.jessyuan.alldemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.jessyuan.alldemo.IRemoteService;

/**
 * Created by Jess Yuan on 13/10/2016.
 */

public class RemoteService extends Service {

    private IRemoteService.Stub binder = new IRemoteService.Stub() {
        @Override
        public int getUID() throws RemoteException {
            return 123;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }



}
