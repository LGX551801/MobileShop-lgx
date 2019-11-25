package com.example.hasee.mobileshop_lgx.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        //黄油刀框架绑定
        ButterKnife.bind(this);
        //添加到activities列表中
        ActivityCollector.addActivity(this);
        System.out.println("ANDROID DEBUG:"+getClass().getSimpleName()+" leng of activitiesList: "+ActivityCollector.activities.size());
        initView();
        initData();
    }

    public abstract int getContentViewId();

    protected void initView() {

    }

    protected void initData() {

    }

    //封装好两个的Toast.makeText方法
    public void toastShort(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();}
    public void toastLong(String msg){Toast.makeText(this,msg,Toast.LENGTH_LONG).show();}


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
