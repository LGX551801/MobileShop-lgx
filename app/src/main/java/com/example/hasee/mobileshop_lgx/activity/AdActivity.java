package com.example.hasee.mobileshop_lgx.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hasee.mobileshop_lgx.R;
import com.example.hasee.mobileshop_lgx.common.BaseActivity;
import com.example.hasee.mobileshop_lgx.common.MobileshopApp;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import butterknife.BindView;
import butterknife.OnClick;


public class AdActivity extends BaseActivity {
    //初始化组件
    @BindView(R.id.iv_image)
    ImageView imageView;
    @BindView(R.id.tv_skip)
    TextView tv_skip;
    @OnClick({R.id.tv_skip})
    //按钮点击事件
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_skip:
                skip();
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_ad;
    }

    @Override
    protected void initView() {
        super.initView();
        String url="http://i1.sinaimg.cn/ent/d/2008-06-04/U105P28T3D2048907F326DT20080604225106.jpg";

        ImageLoader.getInstance().displayImage(url, imageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                //图片加载失败，跳转到主页
                //启动跳转页面倒计时
                MobileshopApp.handler.post(jumpThread);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                MobileshopApp.handler.post(jumpThread);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                MobileshopApp.handler.post(jumpThread);
            }
        });
    }

    private void skip() {
        Intent intent=new Intent(AdActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
        c_count=COUNT;
        MobileshopApp.handler.removeCallbacks(jumpThread);
    }

    private static final String SKIP_TEXT="点击跳过 %d";
    private final static int COUNT=5;
    private final static int DELAYED=1000;
    private int c_count=COUNT;
    private Runnable jumpThread=new Runnable() {
        @Override
        public void run() {
            if(c_count<=0){
                skip();
            }else{
                tv_skip.setText(String.format(SKIP_TEXT,c_count));
                c_count--;
                MobileshopApp.handler.postDelayed(jumpThread,DELAYED);
            }
        }
    };
}