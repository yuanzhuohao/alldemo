package com.example.mylibrary.ui;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;


/**
 * Created by JessYuan on 02/12/2016.
 */

public class CustomDrawable extends View {

    private static final String TAG = "CustomDrawable";
    private static final int LENGTH = 160;

    private RectF mArcRect = new RectF();

    private Paint mArcPaint = new Paint();

    private ValueAnimator mAnimator;

    private int mCurrentValue;

    public CustomDrawable(Context context) {
        super(context);
    }

    public CustomDrawable(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDrawable(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setup();
        setAnimator();
    }

    private void setAnimator() {
        mAnimator = ValueAnimator.ofInt(0, 360);
        mAnimator.setDuration(1000);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.start();
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i(TAG, "onAnimationUpdate: " + animation.getAnimatedFraction());
                Log.i(TAG, "onAnimationUpdate: " + animation.getAnimatedValue());
                mCurrentValue = (int)animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    private void setup() {
        int availableWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        int availableHeight = getHeight() - getPaddingTop() - getPaddingBottom();

        int size = Math.min(availableWidth, availableHeight) - 20;

        int left = getPaddingLeft() + 10;
        int top = getPaddingTop() + 10;

        mArcRect.set(left, top, left + size, top + size);

        mArcPaint.setColor(Color.RED);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(10);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
//        int start;
//        if (mCurrentValue >= LENGTH) {
//            start = mCurrentValue - LENGTH;
//        } else {
//            start = 360 - (LENGTH - mCurrentValue);
//        }
//        canvas.drawArc(mArcRect, start, mCurrentValue, false, mArcPaint);

        canvas.drawArc(mArcRect, mCurrentValue, LENGTH, false, mArcPaint);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAnimator.end();
    }
}
