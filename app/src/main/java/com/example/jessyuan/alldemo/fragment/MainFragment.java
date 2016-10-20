package com.example.jessyuan.alldemo.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.jessyuan.alldemo.R;
import com.example.mylibrary.FragmentUtils;
import com.example.mylibrary.ToastUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.OnClick;

/**
 * Created by Jess Yuan on 17/10/2016.
 */

public class MainFragment extends BaseToolbarFragment {

    private static final int REQUEST_TAKE_PICTURES = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    void setToolbar() {
        getToolbar().setTitle("All Demo");
        getToolbar().inflateMenu(R.menu.toolbar_menu);
        getToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_toolbar_take_a_photo:
                        dispatchTakePictureIntent();
                        ToastUtils.makeTextShort(getActivity(), "Take a Photo");
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    @OnClick(R.id.btn_rxjava_demo)
    void rxjavademo() {
        FragmentUtils.replaceFragment(getFragmentManager(),
                new RxJavaDemoFragment(),
                android.R.id.content);
    }

    @OnClick(R.id.btn_pick_pictures)
    void pickpicture() {
        FragmentUtils.replaceFragment(getFragmentManager(),
                new PickPicturesFragment(),
                android.R.id.content);
    }

    private void dispatchTakePictureIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {


            File file = null;
            try {
                file = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (file != null) {
                Uri photoUri = FileProvider.getUriForFile(getActivity(),
                                        "com.example.jessyuan.alldemo.fileprovider",
                                        file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, REQUEST_TAKE_PICTURES);
            }

        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
