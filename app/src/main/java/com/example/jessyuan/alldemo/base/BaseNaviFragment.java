package com.example.jessyuan.alldemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.jessyuan.alldemo.R;

/**
 * Created by Jess Yuan on 18/10/2016.
 */

public abstract class BaseNaviFragment extends BaseToolbarFragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getToolbar().setDisplayHomeAsUpEnabled(true);
        getToolbar().setHomeAsUpIndicator(R.drawable.ic_navigate_before_white_36dp);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                getFragmentManager().popBackStack();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
