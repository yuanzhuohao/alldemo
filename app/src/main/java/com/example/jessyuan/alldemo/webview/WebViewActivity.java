package com.example.jessyuan.alldemo.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.jessyuan.alldemo.R;
import com.example.mylibrary.FragmentUtils;

/**
 * Created by JessYuan on 19/12/2016.
 */

public class WebViewActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_webview);


        if (getIntent() != null) {
            String url = getIntent().getStringExtra("url");
            String title = getIntent().getStringExtra("title");

            FragmentUtils.addFragmentToActivity(getSupportFragmentManager(),
                    WebViewFragment.newInstance(url, title),
                    R.id.webview_container);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
