package com.example.jessyuan.alldemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.jessyuan.alldemo.R;

/**
 * Created by Jess Yuan on 18/10/2016.
 */

public abstract class BaseNaviFragment extends BaseToolbarFragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getToolbar().setNavigationIcon(R.drawable.ic_navigate_before_white_48dp);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }
}
