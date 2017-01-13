package com.example.jessyuan.alldemo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

/** Not complete
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
