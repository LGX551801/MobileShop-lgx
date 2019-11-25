package com.example.hasee.mobileshop_lgx.http.presenter;

import com.example.hasee.mobileshop_lgx.http.HttpMethods;
import com.example.hasee.mobileshop_lgx.http.entity.GoodsEntity;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class GoodsPresenter extends HttpMethods{

    public static void listByKeywords(Subscriber<List<GoodsEntity>> subscriber, String keywords){
        Observable<List<GoodsEntity>> observable=goodsService.listByKeywords(keywords)
                .map(new HttpResultFunc<List<GoodsEntity>>());
        toSubscribe(observable,subscriber);
    }

    /**
     * 根据二级分类id获取商品列表
     * @param subscriber
     * @param catId
     */
    public static void list(Subscriber<List<GoodsEntity>> subscriber,int catId){
        Observable<List<GoodsEntity>> observable= goodsService.list(catId)
                .map(new HttpMethods.HttpResultFunc<List<GoodsEntity>>());
        toSubscribe(observable,subscriber);
    }
}
