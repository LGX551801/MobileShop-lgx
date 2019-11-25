package com.example.hasee.mobileshop_lgx.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.example.hasee.mobileshop_lgx.R;
import com.example.hasee.mobileshop_lgx.common.BaseActivity;
import com.example.hasee.mobileshop_lgx.fragment.NavigationFragment;

public class MainActivity extends BaseActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        //将fragment添加到Activity中
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=manager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,new NavigationFragment());
        fragmentTransaction.commit();
    }
}
