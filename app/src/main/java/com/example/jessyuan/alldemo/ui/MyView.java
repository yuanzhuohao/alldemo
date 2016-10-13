package com.example.jessyuan.alldemo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.example.mylibrary.LogUtils;

/**
 * Created by Jess Yuan on 09/10/2016.
 */

public class MyView extends View {

    private Context mContext;

    private Scroller mScroller;

    public MyView(Context context) {
        super(context);
        mContext = context;
        mScroller = new Scroller(mContext);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mScroller = new Scroller(mContext);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    int lastX;
    int lastY;
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int x = (int) event.getX();
//        int y = (int) event.getY();
//
//        View parent = ((View)getParent());
//        LogUtils.i("View滑动的距离", parent.getScrollX() + ", " + parent.getScrollY());
//
//        LogUtils.d("触摸事件坐标", x + ", " + y);
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                LogUtils.d("按下的坐标","adfasdf");
//                lastX = x;
//                lastY = y;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int deltaX = x - lastX;
//                int deltaY = y - lastY;
////                layout(getLeft() + deltaX, getTop() + deltaY,
////                        getRight() + deltaX,
////                        getBottom() + deltaY);
////                invalidate();
//
//                ((View)getParent()).scrollBy(-deltaX, -deltaY);
//            case MotionEvent.ACTION_UP:
//                mScroller.startScroll(
//                        parent.getScrollX(),
//                        parent.getScrollY(),
//                        -parent.getScrollX(),
//                        -parent.getScrollY());
//                invalidate();
//                break;
//        }
//        return true;
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.i("MotionEvent", "MyView onTouchEvent");

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.i("MotionEvent", "MyView Down");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.i("MotionEvent", "MyView Move");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.i("MotionEvent", "MyView Up");
                break;
        }

//        return true;
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        if (mScroller.computeScrollOffset()) {
            ((View)getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtils.i("View的位置", left + ", " + top + ", " + ", " + right + ", " + bottom);
    }
}
