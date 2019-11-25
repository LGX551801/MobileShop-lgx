package com.example.hasee.mobileshop_lgx.common;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;

import com.example.hasee.mobileshop_lgx.R;
import com.example.hasee.mobileshop_lgx.http.HttpMethods;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

public class MobileshopApp extends Application {
    public static Handler handler=new Handler();
    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化网络框架
        HttpMethods.getInstance();
        sContext=getApplicationContext();
        //初始化ImageLoader
        initImageLoader();
    }

    private void initImageLoader() {
        //创建默认的DisplayImageOptions
        DisplayImageOptions defalut_options=new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.image_loading)
                .showImageForEmptyUri(R.drawable.image_empty)
                .showImageOnFail(R.drawable.image_error)
                .resetViewBeforeLoading(false)
                .delayBeforeLoading(1000)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(false)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .displayer(new SimpleBitmapDisplayer())
                .handler(new android.os.Handler())
                .build();

        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCacheExtraOptions(480,800)
                .memoryCacheSize(2*1024*1024)
                .memoryCacheSizePercentage(13)
                .diskCacheSize(50*1024*1024)
                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY-2)
                .denyCacheImageMultipleSizesInMemory()
                .imageDownloader(new BaseImageDownloader(getApplicationContext()))
                .defaultDisplayImageOptions(defalut_options)
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(config);
    }

    public static Context getContext(){return sContext;}
}
