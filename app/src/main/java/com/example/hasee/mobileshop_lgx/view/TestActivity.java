package com.example.hasee.mobileshop_lgx.view;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hasee.mobileshop_lgx.R;
import com.example.hasee.mobileshop_lgx.common.BaseActivity;
import com.example.hasee.mobileshop_lgx.http.ProgressDialogSubscriber;
import com.example.hasee.mobileshop_lgx.http.entity.MemberEntity;
import com.example.hasee.mobileshop_lgx.http.presenter.MemberPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class TestActivity extends BaseActivity {
    @BindView(R.id.bt_net_request)
    Button bt_send_request;

    @BindView(R.id.tv_result)
    TextView tv_result;

    @OnClick(R.id.bt_net_request)
    public void onClick(View view){
        httpRequest("123","123");
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_test;
    }

    private void httpRequest(final String username,final String password){
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
                tv_result.setText(
                        String.format("用户名：%s\n邮箱:%s"
                                ,memberEntity.uname
                                ,memberEntity.email)
                );
            }
        },username,password);
    }
}
