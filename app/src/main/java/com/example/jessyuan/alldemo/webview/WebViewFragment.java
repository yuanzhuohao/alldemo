package com.example.jessyuan.alldemo.webview;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.base.BaseNaviFragment;
import com.example.mylibrary.ToastUtils;

import butterknife.BindView;

/**
 * Created by JessYuan on 19/12/2016.
 */

public class WebViewFragment extends BaseNaviFragment {

    @BindView(R.id.webview)
    WebView mWebView;
    @BindView(R.id.progressbar)
    ProgressBar mProgressBar;

    public static WebViewFragment newInstance(String url, String title) {

        Bundle args = new Bundle();

        WebViewFragment fragment = new WebViewFragment();
        args.putString("url", url);
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setContentView(R.layout.fragment_webview);

        setHasOptionsMenu(true);
        setTitle(getArguments().getString("title"));

        mProgressBar.getProgressDrawable().setColorFilter(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                PorterDuff.Mode.SRC_IN);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebviewClient());
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgressBar.setProgress(newProgress);
            }
        });
        mWebView.loadUrl(getArguments().getString("url"));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_webview, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                if (mWebView.canGoBack()) {
                    mWebView.goBack();
                } else {
                    getActivity().finish();
                }
                break;
            case R.id.menu_refresh:
                mWebView.reload();
                break;
            case R.id.menu_close:
                getActivity().finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public class WebviewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mProgressBar.setVisibility(View.GONE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            ToastUtils.makeTextShort(getActivity(), "Oh, load failed. Please refresh!");
        }
    }
}
