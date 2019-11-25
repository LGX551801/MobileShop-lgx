package com.example.hasee.mobileshop_lgx.fragment;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hasee.mobileshop_lgx.R;
import com.example.hasee.mobileshop_lgx.activity.ChangePwdActivity;
import com.example.hasee.mobileshop_lgx.activity.LoginActivity;
import com.example.hasee.mobileshop_lgx.activity.MyAddressActivity;
import com.example.hasee.mobileshop_lgx.activity.MyCollectActivity;
import com.example.hasee.mobileshop_lgx.activity.MyOrderActivity;
import com.example.hasee.mobileshop_lgx.common.BaseFragment;
import com.example.hasee.mobileshop_lgx.utils.SystemCofig;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalFragment extends BaseFragment {
    protected String returnUsername="";
    protected String returnPasswd="";

    //已登录
    @BindView(R.id.personal_for_login)
    RelativeLayout personal_for_login;
    @BindView(R.id.user_img_view)
    ImageView user_img_view;
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.user_level)
    TextView user_level;

    //未登录
    @BindView(R.id.personal_for_not_login)
    RelativeLayout personal_for_not_login;

    //退出登录
    @BindView(R.id.person_logout_layout)
    RelativeLayout person_logout_layout;


    @Override
    protected void initView(View view) {
        super.initView(view);
        //初始化状态配置
        resetUI();
    }

    private void resetUI() {
        if (SystemCofig.isLogin()){
            //已登录，显示已登录的UI，隐藏未登录的UI
            personal_for_login.setVisibility(View.VISIBLE);
            personal_for_not_login.setVisibility(View.GONE);
            person_logout_layout.setVisibility(View.VISIBLE);

            //显示已登录的信息
            //显示头像
            ImageLoader.getInstance().displayImage(SystemCofig.getLoginUserHead(),user_img_view);
            //显示用户名
            user_name.setText(SystemCofig.getLoginUserName());
            //显示邮箱
            user_level.setText(SystemCofig.getLoginUserEmail());
        }else{
            //未登录，显示未登录的UI，隐藏已登录的UI
            personal_for_login.setVisibility(View.GONE);
            personal_for_not_login.setVisibility(View.VISIBLE);
            person_logout_layout.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.personal_login,R.id.person_my_order,R.id.my_collect,
            R.id.my_address,R.id.my_account,R.id.person_logout_layout})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.personal_login:
            {
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                if(!SystemCofig.isLogin()){
                    intent.putExtra("username",returnUsername);
                    intent.putExtra("passwd",returnPasswd);
                }
                startActivityForResult(intent,1000);
            }
            break;
            case R.id.person_my_order:
            {
                //我的订单
                if (SystemCofig.isLogin()){
                    startActivity(new Intent(getActivity(), MyOrderActivity.class));
                }else {
                    Intent intent=new Intent(getActivity(),LoginActivity.class);
                    startActivityForResult(intent,1001);
                }
            }
            break;
            case R.id.my_collect:
            {
                //我的收藏
                if (SystemCofig.isLogin()){
                    startActivity(new Intent(getActivity(), MyCollectActivity.class));
                }else {
                    Intent intent=new Intent(getActivity(),LoginActivity.class);
                    startActivityForResult(intent,1002);
                }
            }
            break;
            case R.id.my_address:
            {
                //我的地址
                if (SystemCofig.isLogin()){
                    startActivity(new Intent(getActivity(), MyAddressActivity.class));
                }else {
                    Intent intent=new Intent(getActivity(),LoginActivity.class);
                    startActivityForResult(intent,1003);
                }
            }
            break;
            case R.id.my_account:
            {
                //修改密码
                if (SystemCofig.isLogin()){
                    startActivity(new Intent(getActivity(), ChangePwdActivity.class));
                }else {
                    Intent intent=new Intent(getActivity(),LoginActivity.class);
                    startActivityForResult(intent,1004);
                }
            }
            break;

            case R.id.person_logout_layout:
            {
                //退出登录
                SystemCofig.logout();
                resetUI();
                toastShort("用户已注销!");
            }
            break;
            default:
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            //重置UI
            resetUI();

            //打开LoginActivity之前想要进入的页面
            Intent intent=new Intent();
            if(requestCode==1000){

            }else if (requestCode==1001){
                intent.setClass(getActivity(),MyOrderActivity.class);
                startActivity(intent);
            }else if (requestCode==1002){
                intent.setClass(getActivity(),MyCollectActivity.class);
                startActivity(intent);
            }else if (requestCode==1003){
                intent.setClass(getActivity(),MyAddressActivity.class);
                startActivity(intent);
            }else if (requestCode==1004){
                intent.setClass(getActivity(),ChangePwdActivity.class);
                startActivity(intent);
            }
        }else if (resultCode==Activity.RESULT_CANCELED){
            if(requestCode==1000){
                //LoginActivity页面输入内容但未登录，收到来自LoginActivity的username和passwd
                returnUsername=data.getStringExtra("username");
                returnPasswd=data.getStringExtra("passwd");
            }
        }


    }

    @Override
    public int getContentId() {
        return R.layout.fragment_personal;
    }


}

