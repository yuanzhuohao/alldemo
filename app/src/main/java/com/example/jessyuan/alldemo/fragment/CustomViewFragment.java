package com.example.jessyuan.alldemo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.base.BaseFragment;
import com.example.mylibrary.ui.BottomNavigatorItemView;
import com.example.mylibrary.ui.BottomNavigatorView;
import com.example.mylibrary.ui.NavigatorItem;
import com.example.mylibrary.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JessYuan on 30/11/2016.
 */

public class CustomViewFragment extends BaseFragment {

    private static final String TAG = "CustomViewFragment";

    @BindView(R.id.bottomnavigatorview)
    BottomNavigatorView mBottomNavigatorView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom_view_2, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBottomNavigatorView.addItem(new NavigatorItem("图标1", R.drawable.selector_press_bottom_navigation));
        mBottomNavigatorView.addItem(new NavigatorItem("图标2", R.drawable.selector_press_bottom_navigation));
        mBottomNavigatorView.addItem(new NavigatorItem("图标3", R.drawable.selector_press_bottom_navigation));
        mBottomNavigatorView.setItemViewSelectedListener(new BottomNavigatorView.OnItemViewSelectedListener() {
            @Override
            public void itemViewSelected(BottomNavigatorItemView itemView, int position) {
                ToastUtils.makeTextShort(getContext(), itemView.getNavigatorItem().getLabel());
            }
        });
    }
}
