package com.example.jessyuan.alldemo.fragment;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.EditText;

import com.example.jessyuan.alldemo.IRemoteService;
import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.service.DownloadSomethingService;
import com.example.jessyuan.alldemo.service.GenerateNumberService;
import com.example.jessyuan.alldemo.service.MessengerService;
import com.example.jessyuan.alldemo.service.RemoteService;
import com.example.mylibrary.LogUtils;
import com.example.mylibrary.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Jess Yuan on 13/10/2016.
 */

public class ServiceDemoFragment extends BaseFragment {

    private final static String TAG = "ServiceDemoFragment";

    @BindView(R.id.et_message)
    EditText massageEditText;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            GenerateNumberService.MyBinder binder = (GenerateNumberService.MyBinder) service;
            mService = binder.getService();
            mBound = true;
            LogUtils.i(TAG, "绑定服务成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
            LogUtils.i(TAG, "绑定服务失败");
        }
    };

    private ServiceConnection mConnectionByMessenger = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ToastUtils.makeTextShort(getActivity(), "绑定服务成功");
            mMessenger = new Messenger(service);
            mBoundByMessenger = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            ToastUtils.makeTextShort(getActivity(), "绑定服务失败");
            mBoundByMessenger = false;
        }
    };

    private ServiceConnection mConnectionByAIDL = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ToastUtils.makeTextShort(getActivity(), "绑定服务成功");
            mRemoteService = IRemoteService.Stub.asInterface(service);
            try {
                ToastUtils.makeTextShort(getActivity(), mRemoteService.getUID() + "");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mBoundByAIDL = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            ToastUtils.makeTextShort(getActivity(), "绑定服务失败");
            mBoundByAIDL = false;
        }
    };

    private GenerateNumberService mService;
    private Messenger mMessenger;
    private IRemoteService mRemoteService;

    private boolean mBound;
    private boolean mBoundByMessenger;
    private boolean mBoundByAIDL;

    @Override
    public int setLayoutViewId() {
        return R.layout.fragment_service_demo;
    }


    @OnClick(R.id.btn_start_service)
    void startService() {
        Intent intent = new Intent(getActivity(), DownloadSomethingService.class);
        getActivity().startService(intent);
    }

    @OnClick(R.id.btn_bind_service_generate_random_number)
    void bindService() {
        Intent intent = new Intent(getActivity(), GenerateNumberService.class);
        getActivity().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @OnClick(R.id.btn_unbind_service_generate_random_number)
    void unbindService() {
        if (mBound) {
            getActivity().unbindService(mConnection);
            mBound = false;
            LogUtils.i(TAG, "解绑成功");
            mService = null;
        }
    }

    @OnClick(R.id.btn_bind_service_with_messenger)
    void bindServiceWithMessenger() {
        Intent intent = new Intent(getActivity(), MessengerService.class);
        getActivity().bindService(intent, mConnectionByMessenger, Context.BIND_AUTO_CREATE);
    }

    @OnClick(R.id.btn_service_unbind_service_with_messenger)
    void unbindServiceWithMessenger() {
        if (mBoundByMessenger) {
            getActivity().unbindService(mConnectionByMessenger);
            mBoundByMessenger = false;
            ToastUtils.makeTextShort(getActivity(), "解绑成功");
            mMessenger = null;
        }
    }

    @OnClick(R.id.btn_generate_number)
    void generatenumber() {
        if (mService != null) {
            LogUtils.i(TAG, mService.getRandomNumber() + "");
        }
    }

    @OnClick(R.id.btn_send_message)
    void sendMessage() {
        if (mMessenger != null) {
            Message message = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
            Bundle bundle = new Bundle();
            bundle.putString("msg", massageEditText.getText().toString());
            message.setData(bundle);
            try {
                mMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.btn_bind_service_by_aidl)
    void bindServiceByAIDL() {
        Intent intent = new Intent(getActivity(), RemoteService.class);
        getActivity().bindService(intent, mConnectionByAIDL, Context.BIND_AUTO_CREATE);
    }

    @OnClick(R.id.btn_unbind_service_by_aidl)
    void unbindServiceByAIDL() {
        if (mBoundByAIDL) {
            getActivity().unbindService(mConnectionByAIDL);
            mBoundByAIDL = false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBound) {
            getActivity().unbindService(mConnection);
            mBound = false;
        }

        if (mBoundByMessenger) {
            getActivity().unbindService(mConnectionByMessenger);
            mBoundByMessenger = false;
        }

        if (mBoundByAIDL) {
            getActivity().unbindService(mConnectionByAIDL);
            mBoundByAIDL = false;
        }
    }

}
