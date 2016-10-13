package com.example.jessyuan.alldemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;

import com.example.mylibrary.ToastUtils;

/**
 * Created by Jess Yuan on 13/10/2016.
 */

public class MessengerService extends Service {

    public static final int MSG_SAY_HELLO = 0;

    private Messenger mMessenger = new Messenger(new IncommingHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    class IncommingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Bundle bundle = msg.getData();
                    String str = bundle.getString("msg");
                    ToastUtils.makeTextShort(getApplicationContext(),
                                            "Service: Hello, I receive!" +
                                                    "Content: " + str);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

}
