package com.example.hasee.mobileshop_lgx.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hasee.mobileshop_lgx.R;
import com.example.hasee.mobileshop_lgx.adapter.GoodsListAdapter;
import com.example.hasee.mobileshop_lgx.common.BaseActivity;
import com.example.hasee.mobileshop_lgx.http.entity.GoodsEntity;
import com.example.hasee.mobileshop_lgx.http.presenter.GoodsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

public class GoodsListActivity extends BaseActivity {
    private int cat_id;

    @BindView(R.id.goodslist_swipe_refresh)
    SwipeRefreshLayout goodslist_swipe_refresh;
    @BindView(R.id.goodslist_recyclerview)
    RecyclerView goodslist_recyclerview;
    @BindView(R.id.goodslist_nodata)
    TextView goodslist_nodata;

    private List<GoodsEntity> listData;
    private GoodsListAdapter adapter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_goods_list;
    }

    @OnClick(R.id.iv_back)
    void close(){
        finish();
    }

    @Override
    protected void initView() {
        super.initView();
        //初始隐藏nodata
        goodslist_nodata.setVisibility(View.GONE);


        //从商品分类界面传递过来的参数
        cat_id=getIntent().getIntExtra("cat_id",0);

        //设置刷新样式
        goodslist_swipe_refresh.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        //下拉刷新监听器
        goodslist_swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                loadData();
            }
        });

        //设置列表样式
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        goodslist_recyclerview.setLayoutManager(linearLayoutManager);

        listData=new ArrayList<GoodsEntity>();
        adapter=new GoodsListAdapter(this,listData);
        adapter.setOnItemClickListener(new GoodsListAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position, GoodsEntity entity) {
                //跳转到商品列表界面
                Intent intent=new Intent(GoodsListActivity.this,GoodsDetailActivity.class);
                intent.putExtra("goods_id",entity.getGoods_id());
                intent.putExtra("goods_name",entity.getName());
                startActivity(intent);
            }
        });
        goodslist_recyclerview.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        super.initData();
        //首次进入页面刷新数据
        loadData();
    }

    private void loadData() {
        GoodsPresenter.list(new Subscriber<List<GoodsEntity>>() {
            @Override
            public void onCompleted() {
                goodslist_swipe_refresh.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                goodslist_swipe_refresh.setRefreshing(false);
            }

            @Override
            public void onNext(List<GoodsEntity> goodsEntities) {
                listData.clear();
                listData.addAll(goodsEntities);
                adapter.notifyDataSetChanged();
                if (listData==null||listData.size()==0){
                    toastShort("没有该列表的商品数据!");
                    goodslist_nodata.setVisibility(View.VISIBLE);
                    goodslist_recyclerview.setVisibility(View.GONE);
                }else {
                    goodslist_nodata.setVisibility(View.GONE);
                    goodslist_recyclerview.setVisibility(View.VISIBLE);
                }
            }
        },cat_id);
    }


}
