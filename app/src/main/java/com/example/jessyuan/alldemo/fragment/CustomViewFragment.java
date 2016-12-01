package com.example.jessyuan.alldemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ImageView;

import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.base.BaseNaviFragment;
import com.example.mylibrary.ui.CircleImageView;

import butterknife.BindView;

/**
 * Created by JessYuan on 30/11/2016.
 */

public class CustomViewFragment extends BaseNaviFragment {

    @BindView(R.id.image_view)
    CircleImageView mCircleImageView;

    @Override
    public void setToolbar(ActionBar toolbar) {
        toolbar.setTitle("Custom View");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setContentView(R.layout.fragment_custom_view);

        mCircleImageView.setImageResource(R.drawable.take_photo);
    }

}
