package com.example.jessyuan.alldemo.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.jessyuan.alldemo.R;
import com.example.mylibrary.LogUtils;

/**
 * Created by Jess Yuan on 20/10/2016.
 */

public class SwipeToLoadLayout extends ViewGroup {

    private View mHeaderView;
    private View mTargetView;
    private View mFooterView;

    private int mHeaderViewHeight;
    private int mTargetViewHeight;
    private int mFooterViewHeight;

    private final static String TAG = "SwipeToLoadLayout";


    public SwipeToLoadLayout(Context context) {
        super(context);
    }

    public SwipeToLoadLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeToLoadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LogUtils.i("TAG", "constructor");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (mHeaderView != null) {
            final View headerView = mHeaderView;
            measureChildWithMargins(headerView, widthMeasureSpec, 0, heightMeasureSpec, 0);
            MarginLayoutParams params = (MarginLayoutParams) headerView.getLayoutParams();
            mHeaderViewHeight = headerView.getMeasuredHeight()
                                + params.topMargin
                                + params.bottomMargin;
        }

        if (mTargetView != null) {
            final View targetView = mTargetView;
            measureChildWithMargins(targetView, widthMeasureSpec, 0, heightMeasureSpec, 0);
            MarginLayoutParams params = (MarginLayoutParams) targetView.getLayoutParams();
            mTargetViewHeight = targetView.getMeasuredHeight()
                    + params.topMargin
                    + params.bottomMargin;
        }

        if (mFooterView != null) {
            final View footerView = mFooterView;
            measureChildWithMargins(footerView, widthMeasureSpec, 0, heightMeasureSpec, 0);
            MarginLayoutParams params = (MarginLayoutParams) footerView.getLayoutParams();
            mFooterViewHeight = footerView.getMeasuredHeight()
                    + params.topMargin
                    + params.bottomMargin;
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        final int childNum = getChildCount();

        if (childNum == 0) {
            return ;
        }

        mHeaderView = findViewById(R.id.swipe_refresh_header);
        mTargetView = findViewById(R.id.swipe_target);
        mFooterView = findViewById(R.id.swipe_load_more_footer);

    }
}
