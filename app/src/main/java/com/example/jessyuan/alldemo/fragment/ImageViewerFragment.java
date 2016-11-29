package com.example.jessyuan.alldemo.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.adapter.ImageViewPagerAdapter;
import com.example.jessyuan.alldemo.base.BaseFragment;
import com.example.jessyuan.alldemo.model.Image;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by JessYuan on 28/11/2016.
 */

public class ImageViewerFragment extends DialogFragment {

    @BindView(R.id.vp_viewpager)
    ViewPager imageViewPager;
    @BindView(R.id.tv_title)
    TextView titleTextView;

    ImageViewPagerAdapter mAdapter;
    List<Image> mImages;

    public static ImageViewerFragment newInstance(List<Image> list, int position) {

        Bundle args = new Bundle();

        ImageViewerFragment fragment = new ImageViewerFragment();
        args.putParcelableArrayList("data", (ArrayList<Image>) list);
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_viewer, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImages = getArguments().getParcelableArrayList("data");
        mAdapter = new ImageViewPagerAdapter(getActivity(), mImages);
        imageViewPager.setAdapter(mAdapter);
        imageViewPager.setOnPageChangeListener(onPageChangeListener);
        setCurrentItem(getArguments().getInt("position"));
    }

    @OnClick(R.id.btn_close_viewer)
    void close() {
        dismiss();
    }


    /**
     * set current item when the item was selected
     * @param position
     */
    private void setCurrentItem(int position) {
        imageViewPager.setCurrentItem(position, false);
        updateTitle(position);
    }

    /**
     * update the title when item was selected
     * @param position
     */
    private void updateTitle(int position) {
        titleTextView.setText((position + 1) + " of " + mImages.size());
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setCurrentItem(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
