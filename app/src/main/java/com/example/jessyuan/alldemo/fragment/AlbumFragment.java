package com.example.jessyuan.alldemo.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.base.BaseNaviFragment;
import com.example.mylibrary.BitmapTransform;
import com.example.mylibrary.BitmapUtils;
import com.example.mylibrary.FileUtils;
import com.example.mylibrary.PicassoUtils;
import com.example.mylibrary.ScreenUtils;
import com.example.mylibrary.ToastUtils;
import com.example.mylibrary.UriUtils;
import com.example.mylibrary.common.CommonRCLVAdapter;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JessYuan on 19/10/2016.
 */

public class AlbumFragment extends BaseNaviFragment {

    private static final int REQUEST_PICK_PICTURES = 0x000001;
    private static final int REQUEST_TAKE_PHOTOS = 0x000002;
    private static final int PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 0x000001;

    @BindView(R.id.rcv_album)
    RecyclerView albumRecyclerView;

    CommonRCLVAdapter<Uri> mAdapter;
    List<Uri> uriList;

    Uri photoUri;

    @Override
    public void setToolbar(Toolbar toolbar) {
        toolbar.setTitle("Album");
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_toolbar_take_a_photo:
                        takePhoto();
                        return true;
                    case R.id.item_toolbar_pick_pictures:
                        pickPictures();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pick_pictures, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {

                } else {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
                }
            }
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PICK_PICTURES && resultCode == getActivity().RESULT_OK
                && data != null) {
            uriList.add(data.getData());
            mAdapter.notifyDataSetChanged();

        } else if (requestCode == REQUEST_TAKE_PHOTOS && resultCode == getActivity().RESULT_OK) {
            if (photoUri != null) {
                uriList.add(photoUri);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    // 拍照
    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            try {
                File photoPath = FileUtils.createImageFile(getActivity());
                photoUri = FileProvider.getUriForFile(getActivity(),
                        getContext().getApplicationContext().getPackageName() + ".fileprovider",
                        photoPath);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, REQUEST_TAKE_PHOTOS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 选择图片
    private void pickPictures() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_PICK_PICTURES);
    }

    private void setupRecyclerView() {
        uriList = new ArrayList<>();
        albumRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mAdapter = new CommonRCLVAdapter<Uri>(R.layout.item_album_image, getContext(), uriList) {
            @Override
            public void onBindViewHolder(CommonViewHolder holder, int position, Uri data) {
                ImageView imageView = holder.getViewById(R.id.iv_item_image);
                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                int width = ScreenUtils.getScreenWidth(getActivity()) / 2;
                params.width = width;
                params.height = width;
                imageView.setLayoutParams(params);
                Picasso.with(getActivity()).load(data)
//                        .transform(new BitmapTransform(100, 100))
                        .resize(50, 50)
//                        .centerCrop()
                        .into(imageView);
            }
        };

        albumRecyclerView.setAdapter(mAdapter);
    }

}
