package com.example.jessyuan.alldemo.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.jessyuan.alldemo.R;
import com.example.mylibrary.ToastUtils;

import butterknife.OnClick;

/**
 * Created by JessYuan on 21/12/2016.
 */

public class PhotoViewer extends ImageView {

    private static final String TAG = "PhotoViewer";

    private float scale = 1f;
    private ScaleGestureDetector mDetector;

    public PhotoViewer(Context context) {
        super(context);
    }

    public PhotoViewer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        mDetector = new ScaleGestureDetector(context, new ScaleListener(this));
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    public PhotoViewer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        float startScale = 0;
        float endScale = 0;

        private View mView;

        public ScaleListener(View view) {
            mView = view;
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            mView.setScaleX(scale);
            mView.setScaleY(scale);
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            Log.i(TAG, "onScaleBegin: ");
            startScale = scale;
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            Log.i(TAG, "onScaleEnd: " + (endScale - startScale));
            super.onScaleEnd(detector);
        }
    }

}
