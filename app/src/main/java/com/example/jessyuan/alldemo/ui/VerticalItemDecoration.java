package com.example.jessyuan.alldemo.ui;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by JessYuan on 13/12/2016.
 */

public class VerticalItemDecoration extends RecyclerView.ItemDecoration {

    private int mVerticalHeight = 0;

    public VerticalItemDecoration(int vPlaceHolder) {
        this.mVerticalHeight = vPlaceHolder;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = mVerticalHeight;
        }
    }
}
