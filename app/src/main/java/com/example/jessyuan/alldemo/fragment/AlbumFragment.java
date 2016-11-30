package com.example.jessyuan.alldemo.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.base.BaseNaviFragment;
import com.example.jessyuan.alldemo.camera.DefaultCameraModule;
import com.example.jessyuan.alldemo.camera.ImageCaptureReadyListener;
import com.example.jessyuan.alldemo.helper.ImageLoader;
import com.example.jessyuan.alldemo.listeners.ImageLoaderListener;
import com.example.jessyuan.alldemo.model.Folder;
import com.example.jessyuan.alldemo.model.Image;
import com.example.jessyuan.alldemo.ui.GridPlacingDecoration;
import com.example.mylibrary.LogUtils;
import com.example.mylibrary.common.CommonRCLVAdapter;
import com.example.permissionmanager.PermissionListener;
import com.example.permissionmanager.PermissionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by JessYuan on 19/10/2016.
 */

public class AlbumFragment extends BaseNaviFragment {

    private static final String TAG = "相册 Fragment";

    private static final int REQUEST_IMAGE_CAPTURE = 0x000001;

    @BindView(R.id.rcv_album)
    RecyclerView albumRecyclerView;
    @BindView(R.id.ll_mainlayout)
    LinearLayout mainLayout;

    CommonRCLVAdapter<Folder> mFolderAdapter;
    CommonRCLVAdapter<Image> mImageAdapter;

    List<Folder> mFolderList = new ArrayList<>();
    List<Image> mImageList = new ArrayList<>();

    Handler mHandler = new Handler(Looper.getMainLooper());
    DefaultCameraModule mCameraModule = new DefaultCameraModule();

    private GridPlacingDecoration mDecoration;

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
            takePhoto();
            return true;
        } else {
            loadDeviceImage();
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
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            mCameraModule.getImage(getActivity(), data, new ImageCaptureReadyListener() {
                @Override
                public void onImageReady(Image image) {
                    loadDeviceImage();
                }
            });
        }
    }

    /**
     * Take a photo
     */
    private void takePhoto() {
        Intent intent = mCameraModule.getCameraIntent(getActivity());
        if (intent != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
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
            public void onBindViewHolder(CommonViewHolder holder, int position, final Folder data) {
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
                        mImageList = data.getImages();
                        setFolderAdapterOnclick(data, data.getImages());
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
                        setImageAdapterOnclick(mImageList, position);
                    }
                });
            }
        };
    }

    /**
     * Set FolderAdapter itemview click listener
     */
    private void setFolderAdapterOnclick(Folder folder, List<Image> list) {
        setImageAdapter(folder, list);
    }

    private void setImageAdapterOnclick(List<Image> list, int position) {
        ImageViewerFragment fragment = ImageViewerFragment.newInstance(list, position);
        fragment.show(getFragmentManager(), "slideshow");
    }

    /**
     * Setup RecyclerView's layout manager, item decoration, and adapter
     */
    private void setRecyclerView(int column, CommonRCLVAdapter adapter) {
        if (mDecoration == null) {
            mDecoration = new GridPlacingDecoration(column,
                    getContext().getResources().getDimensionPixelSize(R.dimen.item_padding), false);
        }

        if (mDecoration != null) {
            albumRecyclerView.removeItemDecoration(mDecoration);
            mDecoration = new GridPlacingDecoration(column,
                    getContext().getResources().getDimensionPixelSize(R.dimen.item_padding), false);
            albumRecyclerView.addItemDecoration(mDecoration);
        }

        albumRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), column));
        albumRecyclerView.setAdapter(adapter);
    }


    /**
     * Set Folder Adapter
     */
    private void setFolderAdapter(List<Folder> folders) {
        if (folders != null) {
            mFolderAdapter.setData(folders);
        }

        setRecyclerView(2, mFolderAdapter);
        updateToolbarTitle("Album");
    }


    /**
     * Set Images Adapter, include setup RecyclerView, update toolbar's title, and toolbar's
     * navigation click listener
     */
    private void setImageAdapter(Folder folder, List<Image> images) {
        if (images != null) {
            mImageAdapter.setData(images);
        }

        setRecyclerView(3, mImageAdapter);
        updateToolbarTitle(folder.getFolderName());
    }


    /**
     * Update toolbar title when change recyclerview adapter
     */
    private void updateToolbarTitle(String title) {
        getToolbar().setTitle(title);
    }

}
