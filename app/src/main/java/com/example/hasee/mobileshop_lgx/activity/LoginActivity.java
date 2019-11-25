package com.example.hasee.mobileshop_lgx.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hasee.mobileshop_lgx.R;
import com.example.hasee.mobileshop_lgx.common.BaseActivity;
import com.example.hasee.mobileshop_lgx.http.ProgressDialogSubscriber;
import com.example.hasee.mobileshop_lgx.http.entity.MemberEntity;
import com.example.hasee.mobileshop_lgx.http.presenter.MemberPresenter;
import com.example.hasee.mobileshop_lgx.utils.SystemCofig;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class LoginActivity extends BaseActivity {
    //初始化组件
    @BindView(R.id.iv_back)
    ImageView iv_back;  //返回按钮
    @BindView(R.id.et_pwd)
    EditText et_pwd;    //密码输入框
    @BindView(R.id.et_username)
    EditText et_username;   //用户名输入框
    @BindView(R.id.bt_login)
    Button bt_login;    //登录按钮
    @BindView(R.id.tv_reg)
    TextView tv_reg;    //注册


    @OnClick({R.id.iv_back,R.id.bt_login,R.id.tv_reg})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
            {
                //返回还未登录的用户名和密码
                goBack();
            }
            break;
            case R.id.bt_login:
            {
                String username=et_username.getText().toString();
                String passwd=et_pwd.getText().toString();
                if (TextUtils.isEmpty(username)){
                    toastShort("请输入用户名");
                    return;
                }
                if (TextUtils.isEmpty(passwd)){
                    toastShort("请输入密码");
                    return;
                }
                //发送登录的接口请求
                login_httpRequest(username,passwd);
            }
            break;
            case R.id.tv_reg:
            {}
            break;
            default:
                toastShort("something wrong");
        }
    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        super.initView();
        try {
            Intent intent=getIntent();
            String returnUsername=intent.getStringExtra("username");
            String returnPasswd=intent.getStringExtra("passwd");
            if(!returnUsername.isEmpty()&&!returnPasswd.isEmpty()){
                et_username.setText(returnUsername);
                et_pwd.setText(returnPasswd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goBack();
    }

    public void goBack(){
        String username=et_username.getText().toString().trim();
        String passwd=et_pwd.getText().toString().trim();
        Intent intent=new Intent();
        intent.putExtra("username",username);
        intent.putExtra("passwd",passwd);
        setResult(RESULT_CANCELED,intent);
        finish();
    }

    private void login_httpRequest( String username, String password){
        MemberPresenter.login2(new ProgressDialogSubscriber<MemberEntity>(this){

            @Override
            public void onCompleted() {
                super.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("error","e:"+e.getMessage().toString());
            }

            @Override
            public void onNext(MemberEntity memberEntity) {
                //保存登录状态
                SystemCofig.setLogin(true);
                //弹出登录成功提示
                toastShort("登录成功!");
                //保存登录账户的信息
                SystemCofig.setLoginUserName(memberEntity.uname);
                SystemCofig.setLoginUserEmail(memberEntity.email);
                SystemCofig.setLoginUserHead(memberEntity.image);
                //返回数据，只有调用了setResult，在调用的地方才会回调onActivityResult方法
                setResult(RESULT_OK);
                finish();
            }
        },username,password);
    }
}

