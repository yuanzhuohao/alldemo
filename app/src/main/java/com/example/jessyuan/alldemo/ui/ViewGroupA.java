package com.example.jessyuan.alldemo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.mylibrary.LogUtils;

/**
 * Created by Jess Yuan on 09/10/2016.
 */

public class ViewGroupA extends LinearLayout {


    public ViewGroupA(Context context) {
        super(context);
    }

    public ViewGroupA(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupA(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtils.i("MotionEvent", "ViewGroupA onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.i("MotionEvent", "ViewGroupA onTouchEvent");

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.i("MotionEvent", "ViewGroupA Down");
//                return true;
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.i("MotionEvent", "ViewGroupA Move");

                break;
            case MotionEvent.ACTION_UP:
                LogUtils.i("MotionEvent", "ViewGroupA Up");

                break;
        }

        return super.onTouchEvent(event);
//        return true;
    }
}
