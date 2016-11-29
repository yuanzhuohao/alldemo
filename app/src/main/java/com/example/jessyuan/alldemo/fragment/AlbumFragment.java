package com.example.jessyuan.alldemo.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.base.BaseNaviFragment;
import com.example.jessyuan.alldemo.helper.ImageLoader;
import com.example.jessyuan.alldemo.listeners.ImageLoaderListener;
import com.example.jessyuan.alldemo.model.Folder;
import com.example.jessyuan.alldemo.model.Image;
import com.example.jessyuan.alldemo.ui.GridPlacingDecoration;
import com.example.mylibrary.FragmentUtils;
import com.example.mylibrary.LogUtils;
import com.example.mylibrary.PermissionUtils;
import com.example.mylibrary.ToastUtils;
import com.example.mylibrary.common.CommonRCLVAdapter;
import com.example.permissionmanager.PermissionListener;
import com.example.permissionmanager.PermissionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.example.jessyuan.alldemo.R.id.image;

/**
 * Created by JessYuan on 19/10/2016.
 */

public class AlbumFragment extends BaseNaviFragment {

    private static final String TAG = "相册 Fragment";

    private static final int REQUEST_PICK_PICTURES = 0x000001;

    @BindView(R.id.rcv_album)
    RecyclerView albumRecyclerView;
    @BindView(R.id.ll_mainlayout)
    LinearLayout mainLayout;

    CommonRCLVAdapter<Folder> mFolderAdapter;
    CommonRCLVAdapter<Image> mImageAdapter;

    List<Folder> mFolderList = new ArrayList<>();
    List<Image> mImageList = new ArrayList<>();

    Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void setToolbar(ActionBar toolbar) {
        toolbar.setTitle("Album");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home && getToolbar().getTitle().equals("Album")) {
            getFragmentManager().popBackStack();
            return true;
        } else if (id == R.id.item_toolbar_camera) {

            return true;
        } else {
            setFolderAdapter(mFolderList);
            return true;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setContentView(R.layout.fragment_pick_pictures);

        PermissionManager.askPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE,
                "Grant Read external storage can load all images from your device",
                new PermissionListener() {
                    @Override
                    public void onResult(boolean permissionGranted) {
                        loadDeviceImage();
                    }
                });
        setupAdapter();
        setFolderAdapter(mFolderList);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    // 拍照
    private void takePhoto() {

    }

    // 选择图片
    private void pickPictures() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_PICK_PICTURES);
    }

    /**
     * Load Device Images from external storage
     */
    private void loadDeviceImage() {
        ImageLoader imageLoader = new ImageLoader(getActivity());
        imageLoader.loadDeviceImages(new ImageLoaderListener() {
            @Override
            public void onImageLoaded(final List<Image> images, final List<Folder> folders) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mFolderAdapter.setData(folders);

                        mFolderList.clear();
                        mFolderList.addAll(folders);
                    }
                });

            }

            @Override
            public void onFailed(Exception ex) {
                LogUtils.e("加载图片失败", ex.getMessage());
            }
        });
    }

    /**
     * Create FolderAdapter and ImageAdapter
     */
    private void setupAdapter() {
        mFolderAdapter = new CommonRCLVAdapter<Folder>(R.layout.item_folder, getContext(), mFolderList) {
            @Override
            public void onBindViewHolder(CommonViewHolder holder, int position,final Folder data) {
                holder.getTextViewById(R.id.tv_item_folder_name).setText(data.getFolderName());
                holder.getTextViewById(R.id.tv_item_image_number).setText(String.valueOf(data.getImages().size()));

                Glide.with(getActivity())
                        .load(data.getImages().get(0).getPath())
                        .placeholder(R.drawable.image_placeholder)
                        .error(R.drawable.image_placeholder)
                        .into(holder.getImageViewById(R.id.iv_item_image));

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setupFolderAdapterOnclick(data, data.getImages());
                    }
                });
            }
        };

        mImageAdapter = new CommonRCLVAdapter<Image>(R.layout.item_image, getContext(), mImageList) {
            @Override
            public void onBindViewHolder(CommonViewHolder holder, final int position, final Image data) {
                Glide.with(getActivity())
                        .load(data.getPath())
                        .placeholder(R.drawable.image_placeholder)
                        .error(R.drawable.image_placeholder)
                        .into(holder.getImageViewById(R.id.iv_item_image));

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setupImageAdapterOnclick(data, mData, position);
                    }
                });
            }
        };
    }

    /**
     * Set FolderAdapter itemview click listener
     * @param folder
     * @param list
     */
    private void setupFolderAdapterOnclick(Folder folder, List<Image> list) {
        setImageAdapter(folder, list);
    }

    private void setupImageAdapterOnclick(Image image, List<Image> list, int position) {
        ImageViewerFragment fragment = ImageViewerFragment.newInstance(list, position);
        fragment.show(getFragmentManager(), "slideshow");
    }

    /**
     * Setup RecyclerView's layout manager, item decoration, and adapter
     * @param column
     * @param adapter
     */
    private void setupRecyclerView(int column, CommonRCLVAdapter adapter) {
        albumRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), column));
        albumRecyclerView.addItemDecoration(new GridPlacingDecoration(column,
                getContext().getResources().getDimensionPixelSize(R.dimen.item_padding), false));
        albumRecyclerView.setAdapter(adapter);
    }


    /**
     * Set Folder Adapter
     * @param folders
     */
    private void setFolderAdapter(List<Folder> folders) {
        if (folders != null) {
            mFolderAdapter.setData(folders);
        }

        setupRecyclerView(2, mFolderAdapter);
        updateToolbarTitle("Album");
    }


    /**
     * Set Images Adapter, include setup RecyclerView, update toolbar's title, and toolbar's navigation
     * click listener
     * @param folder
     * @param images
     */
    private void setImageAdapter(Folder folder, List<Image> images) {
        if (images != null) {
            mImageAdapter.setData(images);
        }

        setupRecyclerView(3, mImageAdapter);
        updateToolbarTitle(folder.getFolderName());
    }


    /**
     * Update Toolbar Title
     * @param title
     */
    private void updateToolbarTitle(String title) {
        getToolbar().setTitle(title);
    }

}
