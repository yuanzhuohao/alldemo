package com.example.jessyuan.alldemo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.mylibrary.LogUtils;

/**
 * Created by Jess Yuan on 09/10/2016.
 */

public class ViewGroupB extends LinearLayout {


    public ViewGroupB(Context context) {
        super(context);
    }

    public ViewGroupB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtils.i("MotionEvent", "ViewGroupB onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.i("MotionEvent", "ViewGroupB onTouchEvent");

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.i("MotionEvent", "ViewGroupB Down");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.i("MotionEvent", "ViewGroupB Move");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.i("MotionEvent", "ViewGroupB Up");
//                return true;
                break;
        }

        return super.onTouchEvent(event);
//        return true;
    }
}
