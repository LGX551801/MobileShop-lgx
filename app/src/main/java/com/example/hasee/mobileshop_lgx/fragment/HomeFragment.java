package com.example.hasee.mobileshop_lgx.fragment;

import android.graphics.Bitmap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hasee.mobileshop_lgx.R;
import com.example.hasee.mobileshop_lgx.common.BaseFragment;
import com.example.hasee.mobileshop_lgx.view.MyWebView;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {
    private static final String TAG="HomeFragment";

//    @BindView(R.id.swipe_fresh_layout)
//    SwipeRefreshLayout mSwipeRefreshLayout;
//
//    @BindView(R.id.webview)
//    MyWebView myWebView;
//
//    @OnClick(R.id.home_title_search)
//    void search(){
//        toastShort("开发中...");
//    }

    @Override
    public int getContentId() {
        return R.layout.fragment_home;
    }

//    @Override
//    protected void initView(View view) {
//        super.initView(view);
//        initWebView();
//        initSwipeRefreshLayout();
//    }
//
//    private void initSwipeRefreshLayout() {
//        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                myWebView.reload();
//            }
//        });
//    }
//
//    private void initWebView() {
//        myWebView.setVerticalScrollBarEnabled(false);
//        myWebView.setHorizontalScrollBarEnabled(false);
//
//        WebSettings settings=myWebView.getSettings();
//        settings.setJavaScriptEnabled(true);
//        settings.setSupportZoom(false);
//        settings.setBuiltInZoomControls(false);
//        myWebView.setOnScrollChangeListener(new MyWebView.IWebViewScroll() {
//            @Override
//            public void onTop() {
//                mSwipeRefreshLayout.setEnabled(true);
//            }
//
//            @Override
//            public void notOnTop() {
//                mSwipeRefreshLayout.setEnabled(false);
//            }
//        });
//        myWebView.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
//                if (keyEvent.getAction()==KeyEvent.ACTION_DOWN){
//                    if (keyCode==KeyEvent.KEYCODE_BACK&&myWebView.canGoBack()){
//                        myWebView.goBack();
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });
//        myWebView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                mSwipeRefreshLayout.setRefreshing(true);
//                Log.e(TAG,"onPageStarted");
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                mSwipeRefreshLayout.setRefreshing(false);
//                Log.e(TAG,"onPageFinished");
//            }
//        });
//        myWebView.loadUrl("http://www.apple.com/cn-k12/shop");
//    }
}

