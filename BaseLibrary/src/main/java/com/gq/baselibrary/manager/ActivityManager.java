package com.gq.baselibrary.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * @Author Created by 王瑞铭
 * @Time   Created by 2018/9/18
 * @Document Activity的管理类
 */
public class ActivityManager {

    public Stack<Activity> activityStack;

    private static class ActivityInstanceManager{
        private static final ActivityManager instance = new ActivityManager();
    }

    /**
     * 单一实例
     */
    public static ActivityManager getInstance(){
        return ActivityInstanceManager.instance;
    }
    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity){
        if(activityStack==null){
            activityStack=new Stack<Activity>();
        }
        activityStack.add(activity);
    }
    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity(){
        Activity activity=activityStack.lastElement();
        return activity;
    }
    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity(){
        Activity activity=activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity){
        try {
            if(activity!=null){
                activityStack.remove(activity);
                activity.finish();
                activity=null;
            }
        }catch (Exception e){

        }
    }
    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls){
        try {
            for (Activity activity : activityStack) {
                if(activity.getClass().equals(cls) ){
                    finishActivity(activity);
                }
            }
        }catch (Exception e){
        }
    }
    /**
     * 结束所有Activity
     */
    public void finishAllActivity(){
        try {
            for (int i = 0, size = activityStack.size(); i < size; i++){
                if (null != activityStack.get(i)){
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        }catch (Exception e){
        }
    }
    /**
     * 退出应用程序
     */
    @SuppressLint("MissingPermission")
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            android.app.ActivityManager activityMgr= (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) { }
    }
}
