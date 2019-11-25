package com.example.hasee.mobileshop_lgx.activity;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.hasee.mobileshop_lgx.R;
import com.example.hasee.mobileshop_lgx.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AnimationActivity extends BaseActivity {
    @BindView(R.id.tv_property)
    TextView tv_property;
    @BindView(R.id.bt_animation)
    Button btn_animation;
    @OnClick(R.id.bt_animation)
    public void startAnimation(){
        tv_property.startAnimation(animation);
    }
    Animation animation;

    @Override
    public int getContentViewId() {
        return R.layout.activity_animation;

    }

    @Override
    protected void initView() {
        super.initView();
        animation = AnimationUtils.loadAnimation(this,R.anim.anim_set);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}

