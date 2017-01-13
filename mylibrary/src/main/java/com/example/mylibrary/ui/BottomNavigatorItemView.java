package com.example.mylibrary.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylibrary.R;

/**
 * Created by JessYuan on 30/12/2016.
 */

public class BottomNavigatorItemView extends FrameLayout {

    private ImageView mIcon;
    private TextView mLabel;
    private View mNotify;
    private int mPosition;
    private boolean mSelected;

    private NavigatorItem mNavigatorItem;

    private Context mContext;

    public BottomNavigatorItemView(Context context) {
        this(context, null);
    }

    public BottomNavigatorItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigatorItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        LayoutInflater.from(context).inflate(R.layout.bottom_navigator_item_view, this, true);

        mIcon = (ImageView) findViewById(R.id.iv_bottom_navigator_item_icon);
        mLabel = (TextView) findViewById(R.id.tv_bottom_navigator_item_label);
        mNotify = findViewById(R.id.v_bottom_navigator_item_notify);
    }

    /**
     * Set Icon and set NavigatorItem iconResId at the same time
     */
    public void setIcon(int resId) {
        Drawable drawable = mContext.getResources().getDrawable(resId);
        mIcon.setImageDrawable(drawable);
        mNavigatorItem.setIconResId(resId);
    }

    /**
     * Set Label and set NavigatorItem label at the same time
     */
    public void setLabel(String label) {
        if (TextUtils.isEmpty(label)) {
            mLabel.setVisibility(GONE);
        } else {
            mLabel.setVisibility(VISIBLE);
            mLabel.setText(label);
        }
        mNavigatorItem.setLabel(label);
    }

    public void setNotify(boolean notify) {
        if (notify) {
            mNotify.setVisibility(VISIBLE);
        } else {
            mNotify.setVisibility(GONE);
        }
        mNavigatorItem.setNotify(notify);
    }

    /**
     * Get ItemView position of BottomNavigatorView
     */
    public int getItemPosition() {
        return mPosition;
    }

    /**
     * Set ItemView position of BottomNavigatorView
     */
    public void setItemPosition(int itemPosition) {
        mPosition = itemPosition;
    }

    public NavigatorItem getNavigatorItem() {
        return mNavigatorItem;
    }

    public void setNavigatorItem(NavigatorItem navigatorItem) {
        mNavigatorItem = navigatorItem;
        updateView();
    }

    public void setLabelColor(int color) {
        mLabel.setTextColor(color);
    }


    /**
     * Update BottomNavigatorItemView. If change NavigatorItem out of BottomNavigatorView, do the
     * best call BottomNavigatorView 'updateItemView' method to update BottomNavigatorView layout
     */
    public void updateView() {
        setLabel(mNavigatorItem.getLabel());
        setIcon(mNavigatorItem.getIconResId());
        setNotify(mNavigatorItem.isNotify());
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void setSelected(boolean selected) {
        mSelected = selected;
        mIcon.setSelected(selected);
    }
}
