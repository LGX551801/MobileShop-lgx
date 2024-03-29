package com.example.hasee.mobileshop_lgx.common;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
    public static List<Activity> activities=new ArrayList<>();

    //每启动一个新的活动就添加到activities
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    //每移除一个活动就从activities移除
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    //移除所有活动
    public static void finishAll(){
        for (Activity activity:activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
        activities.clear();
    }
}

