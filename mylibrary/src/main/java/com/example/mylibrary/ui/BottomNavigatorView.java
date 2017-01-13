package com.example.mylibrary.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.util.Pools;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JessYuan on 30/12/2016.
 */

public class BottomNavigatorView extends ViewGroup {

    private List<NavigatorItem> mItems;
    private BottomNavigatorItemView[] mItemViews;
    private static final Pools.Pool<BottomNavigatorItemView> mPools =
            new Pools.SynchronizedPool<>(5);

    private OnClickListener mClickListener;
    private OnItemViewSelectedListener mItemViewSelectedListener;

    public BottomNavigatorView(Context context) {
        super(context);
    }

    public BottomNavigatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mItems = new ArrayList<>();

        // setting each item view click listener
        mClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavigatorItemView itemView = (BottomNavigatorItemView) v;
                // set items checked
                setAllItemChecked(itemView.getItemPosition());

                if (mItemViewSelectedListener != null) {
                    mItemViewSelectedListener.itemViewSelected(itemView, itemView.getItemPosition());
                }
            }
        };
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int width = MeasureSpec.getSize(widthMeasureSpec);
        final int count = getChildCount();

        final int childState = 0;
        final int heightSpec = MeasureSpec.makeMeasureSpec(150, MeasureSpec.EXACTLY);

        final int[] childWidths = new int[count];
        final int childWidth = width / count;
        for (int i = 0; i < count; i++) {
            childWidths[i] = childWidth;
        }

        int totalWidth = 0;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }

            child.measure(MeasureSpec.makeMeasureSpec(childWidths[i], MeasureSpec.EXACTLY), heightSpec);
            ViewGroup.LayoutParams params = child.getLayoutParams();
            params.width = child.getMeasuredWidth();
            child.setLayoutParams(params);
            totalWidth += child.getMeasuredWidth();
        }

        setMeasuredDimension(
                ViewCompat.resolveSizeAndState(totalWidth,
                        MeasureSpec.makeMeasureSpec(totalWidth, MeasureSpec.EXACTLY), childState),
                ViewCompat.resolveSizeAndState(heightSpec,
                        MeasureSpec.makeMeasureSpec(heightSpec, MeasureSpec.EXACTLY),
                        childState << MEASURED_HEIGHT_STATE_SHIFT)
        );
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();
        final int width = right - left;
        final int height = bottom - top;
        int used = 0;
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            if (ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                child.layout(width - used - child.getMeasuredWidth(), 0, width - used, height);
            } else {
                child.layout(used, 0, child.getMeasuredWidth() + used, height);
            }
            used += child.getMeasuredWidth();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(1);
        paint.setColor(Color.GRAY);

        canvas.drawLine(getPaddingLeft(), 1, getRight() - getPaddingRight(), 1, paint);
    }

    /**
     * Add Bottom ItemView from BottomNavigatorItem
     * @param item
     */
    public void addItem(NavigatorItem item) {
        if (mItems.size() == 0) {
            item.setChecked(true);
        }
        item.setPosition(mItems.size());
        mItems.add(item);
        buildItemView();
    }

    /**
     * Remove Bottom ItemView
     * @param index
     */
    public void remove(int index) {
        mItems.remove(index);
        buildItemView();
    }

    /**
     * Update Bottom ItemView
     * @param index
     * @param item
     */
    public void updateItemView(int index, NavigatorItem item) {
        if (index < 0 && index >= mItems.size()) {
            throw new IndexOutOfBoundsException("Index Out of Bounds on ItemViews");
        }

        mItems.remove(index);
        mItems.add(index, item);
        buildItemView();
    }

    public void updateItemView() {
        buildItemView();
    }

    public NavigatorItem getNavigatorItem(int index) {
        if (index < 0 && index >= mItems.size()) {
            throw new IndexOutOfBoundsException("Index Out of Bounds on ItemViews");
        }

        return mItems.get(index);
    }

    public void setLabelColor(int color) {

    }

    private void buildItemView() {
        if (mItemViews != null) {
            for (BottomNavigatorItemView itemView : mItemViews) {
                mPools.release(itemView);
            }
        }
        removeAllViews();

        mItemViews = new BottomNavigatorItemView[mItems.size()];
        for (int i = 0; i < mItems.size(); i++) {
            NavigatorItem item = mItems.get(i);
            BottomNavigatorItemView itemView = getNewItemView();
            itemView.setNavigatorItem(item);
            itemView.setItemPosition(i);
            itemView.setOnClickListener(mClickListener);
            if (i == 0) {
                itemView.setSelected(true);
            } else {
                itemView.setSelected(false);
            }
            mItemViews[i] = itemView;
            addView(itemView);
        }

        requestLayout();
    }

    private BottomNavigatorItemView getNewItemView() {
        BottomNavigatorItemView itemView = mPools.acquire();
        if (itemView == null) {
            itemView = new BottomNavigatorItemView(getContext());
        }

        return itemView;
    }

    private void setAllItemChecked(int position) {
        BottomNavigatorItemView itemView = mItemViews[position];
        if (itemView.isSelected()) {
            return;
        } else {
            itemView.setSelected(true);
            for (int i = 0; i < mItemViews.length; i++) {
                if (i != position) {
                    mItemViews[i].setSelected(false);
                }
            }
        }
    }

    public void setItemViewSelectedListener(OnItemViewSelectedListener listener) {
        mItemViewSelectedListener = listener;
    }

    public interface OnItemViewSelectedListener {
        void itemViewSelected(BottomNavigatorItemView itemView, int position);
    }
}
