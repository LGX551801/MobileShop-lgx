package com.example.hasee.mobileshop_lgx.activity;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.hasee.mobileshop_lgx.R;
import com.example.hasee.mobileshop_lgx.common.BaseActivity;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.splash_loading_item)
    ImageView splash_loading_item;

    @Override
    public int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        super.initView();

        Animation translate = AnimationUtils.loadAnimation(this,R.anim.splash_loading);
        translate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this,AdActivity.class));
                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
                finish();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        //启动动画
        splash_loading_item.setAnimation(translate);
    }
}