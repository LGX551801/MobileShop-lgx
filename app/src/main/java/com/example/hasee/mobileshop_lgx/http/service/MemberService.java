package com.example.hasee.mobileshop_lgx.http.service;

import com.example.hasee.mobileshop_lgx.http.entity.HttpResult;
import com.example.hasee.mobileshop_lgx.http.entity.MemberEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface MemberService {

    @FormUrlEncoded
    @POST("member/login2")
    Observable<HttpResult<MemberEntity>> login2(
            @Field("input") String input,
            @Field("password") String password
    );

    //用户注册
    @FormUrlEncoded
    @POST("member")
    Observable<HttpResult<MemberEntity>> register(
            @Field("uname")String uname,
            @Field("password")String password,
            @Field("email") String email
    );

    //修改密码
    @FormUrlEncoded
    @PUT("member/{memberId}")
    Observable<HttpResult> changePassword(
            @Path("memberId") String memberId,
            @Query("old_pwd") String old_pwd,
            @Query("new_pwd")String new_pwd
    );
}
