package com.example.hasee.mobileshop_lgx.http.presenter;

import com.example.hasee.mobileshop_lgx.http.HttpMethods;
import com.example.hasee.mobileshop_lgx.http.entity.MemberEntity;

import rx.Observable;
import rx.Subscriber;

public class MemberPresenter extends HttpMethods {
    //用户注册
    public static void register(Subscriber<MemberEntity> subscriber, String username, String email, String password){
        Observable observable=memberService.register(username,password,email)
                .map(new HttpResultFunc<MemberEntity>());
        toSubscribe(observable,subscriber);
    }

    //用户登录
    public static void login2(Subscriber<MemberEntity> subscriber,String username,String password){
        Observable observable=memberService.login2(username,password)
                .map(new HttpResultFunc<MemberEntity>());
        toSubscribe(observable,subscriber);
    }

    //修改密码
    public static void changePassword(Subscriber subscriber,String memeberId,String old_pwd,String new_pwd){
        Observable observable=memberService.changePassword(memeberId,old_pwd,new_pwd);
        toSubscribe(observable,subscriber);
    }
}
