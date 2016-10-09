package com.example.mylibrary.common;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Jess Yuan on 23/09/2016.
 */

public class CommonViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> childView;

    public CommonViewHolder(View itemView) {
        super(itemView);
        childView = new SparseArray<>();
    }

    public View getViewById(int viewId) {
        View view = childView.get(viewId);

        if (view == null) {
            view = itemView.findViewById(viewId);
            childView.put(viewId, view);
        }

        return view;
    }

    public TextView getTextViewById(int viewId) {
        return (TextView)getViewById(viewId);
    }

    public void setViewClick(View view, View.OnClickListener listener) {
        view.setOnClickListener(listener);
    }

}
