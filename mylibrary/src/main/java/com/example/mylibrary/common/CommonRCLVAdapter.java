package com.example.mylibrary.common;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylibrary.R;

import java.util.List;

/**
 * Created by Jess Yuan on 26/09/2016.
 */

public abstract class CommonRCLVAdapter<T> extends RecyclerView.Adapter<CommonRCLVAdapter.CommonViewHolder> {

    private int mItemLayoutId;
    private Context mContext;
    private List<T> mData;

    private RecyclerView mRecyclerView;

    public CommonRCLVAdapter(int itemLayoutId, Context context, List<T> data) {
        mItemLayoutId = itemLayoutId;
        mContext = context;
        this.mData = data;
    }

    public CommonRCLVAdapter(RecyclerView recyclerView, int itemLayoutId, Context context, List<T> data) {
        mItemLayoutId = itemLayoutId;
        mContext = context;
        this.mData = data;
        mRecyclerView = recyclerView;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mItemLayoutId, parent, false);


        return new CommonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        if (position != mData.size()) {
            onBindViewHolder(holder, position, mData.get(position));
        }
    }

    public abstract void onBindViewHolder(CommonViewHolder holder, int position, T data);

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public void setData(List<T> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        int startIndex = mData.size();
        this.mData.addAll(data);
        notifyItemRangeInserted(startIndex, data.size());
    }

    public static class CommonViewHolder extends RecyclerView.ViewHolder {

        private SparseArray<View> childView;

        public CommonViewHolder(View itemView) {
            super(itemView);
            childView = new SparseArray<>();
        }

        public <T>T getViewById(int viewId) {
            View view = childView.get(viewId);

            if (view == null) {
                view = itemView.findViewById(viewId);
                childView.put(viewId, view);
            }

            return (T)view;
        }

        public TextView getTextViewById(int viewId) {
            return (TextView)getViewById(viewId);
        }

        public ImageView getImageViewById(int viewId) {
            return (ImageView)getViewById(viewId);
        }

        public void setViewClick(View view, View.OnClickListener listener) {
            view.setOnClickListener(listener);
        }

    }

}
