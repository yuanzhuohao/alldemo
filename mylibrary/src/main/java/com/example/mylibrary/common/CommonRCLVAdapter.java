package com.example.mylibrary.common;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mylibrary.R;

import java.util.List;

/**
 * Created by Jess Yuan on 26/09/2016.
 */

public abstract class CommonRCLVAdapter<T> extends RecyclerView.Adapter<CommonRCLVAdapter.CommonViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private OnLoadMoreListener mOnLoadMoreListener;

    private int mItemLayoutId;
    private Context mContext;
    private List<T> data;

    private boolean isLoading = false;
    private int visibleThreshold = 2;
    private int lastVisibleView, totalItemCount;

    private RecyclerView mRecyclerView;

    public CommonRCLVAdapter(int itemLayoutId, Context context, List<T> data) {
        mItemLayoutId = itemLayoutId;
        mContext = context;
        this.data = data;
    }

    public CommonRCLVAdapter(RecyclerView recyclerView, int itemLayoutId, Context context, List<T> data) {
        mItemLayoutId = itemLayoutId;
        mContext = context;
        this.data = data;
        mRecyclerView = recyclerView;

        setRecyclerViewScrollingListener();
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == VIEW_TYPE_ITEM) {
            view = LayoutInflater.from(mContext).inflate(mItemLayoutId, parent, false);
        } else if (viewType == VIEW_TYPE_LOADING) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_loading,parent, false);
        }

        return new CommonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        if (position != data.size()) {
            onBindViewHolder(holder, position, data.get(position));
        }
    }

    public abstract void onBindViewHolder(CommonViewHolder holder, int position, T data);

    @Override
    public int getItemCount() {
        if (mOnLoadMoreListener == null) {
            return data.size();
        } else {
            return data.size() + 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mOnLoadMoreListener == null) {
            return VIEW_TYPE_ITEM;
        } else {
            return position == data.size() ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
        }
    }

    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mOnLoadMoreListener = listener;
    }

    public void loaded() {
        isLoading = false;
    }

    private void setRecyclerViewScrollingListener() {
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleView = linearLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && (totalItemCount <= (lastVisibleView + visibleThreshold))) {
                    if (mOnLoadMoreListener != null) {
                        isLoading = true;
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                mOnLoadMoreListener.onLoadMore();

                            }
                        });
                    }

                }
            }
        });
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

        public void setViewClick(View view, View.OnClickListener listener) {
            view.setOnClickListener(listener);
        }

    }

}
