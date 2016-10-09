package com.example.jessyuan.alldemo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jessyuan.alldemo.R;
import com.example.mylibrary.common.CommonRCLVAdapter;
import com.example.mylibrary.common.CommonViewHolder;
import com.example.mylibrary.common.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Jess Yuan on 26/09/2016.
 */

public class LoadMoreFragment extends BaseFragment {

    @BindView(R.id.rclv_load_more)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefreshLayout;

    private List<String> data;
    private CommonRCLVAdapter<String> mAdapter;

    @Override
    public int setLayoutViewId() {
        return R.layout.fragment_load_more;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setData();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new CommonRCLVAdapter<String>(mRecyclerView, android.R.layout.simple_list_item_1, mActivity, data) {
            @Override
            public void onBindViewHolder(CommonViewHolder holder, int position, String data) {
                holder.setIsRecyclable(false);
                if (position % 2 == 0) {
                    holder.getTextViewById(android.R.id.text1).setText(data);
                } else {
                    holder.getTextViewById(android.R.id.text1).setText("Even");
                    holder.itemView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
            }
        };

        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int size = data.size();
                        for (int i = size; i < size + 20; i++) {
                            data.add("Test " + (i + 1));
                        }

                        mAdapter.loaded();
                        mAdapter.notifyItemRangeChanged(data.size() - 1, 20);
                    }
                }, 3000);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();

                        for (int i = 0; i < 20; i++) {
                            data.add("Test " + (i + 1));
                        }

                        mAdapter.notifyDataSetChanged();

                        mRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    private void setData() {
        data = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            data.add("Test " + (i + 1));
        }
    }
}
