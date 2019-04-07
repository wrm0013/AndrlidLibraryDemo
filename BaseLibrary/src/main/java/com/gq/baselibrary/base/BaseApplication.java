package com.gq.baselibrary.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import com.gq.baselibrary.https.HttpManager;
import com.gq.baselibrary.manager.ActivityManager;

import java.util.HashMap;
import java.util.Locale;

/**
 * @Author Created by 王瑞铭
 * @Time   Created by 2018/9/20
 * @Document 配置全局变量
 */
public abstract class BaseApplication extends Application {

    private Application mContext;

    /**
     * 初始化 应用程序级别 的资源，如全局对象、环境配置变量、图片资源初始化、推送服务的注册等
     *
     * 请不要执行耗时操作，否则会拖慢应用程序启动速度
     */
    protected abstract void initConfig();

    /**
     * 数据传递的Map
     */
    private HashMap<Object,Object> intentMap = new HashMap<>();
    /**
     * 临时缓存数据的Map
     */
    private HashMap<Object,Object> cacheMap = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        regActivityLifecycleCallbacks();
        initConfig();
    }

    public void initialize(Application context){
        this.mContext = context;
        HttpManager.getInstance().initialize(context);
    }

    public Context getContext() {
        if (mContext == null)
            throw new ExceptionInInitializerError("请先初始化！");
        return mContext;
    }

    /**
     * 根据key从传值缓存中提取数据
     * @param key
     * @return
     */
    public Object getValueFromIntentMap(Object key){
        if (intentMap.containsKey(key))
            return intentMap.get(key);
        return "";
    }

    /**
     * 根据key从缓存中提取数据
     * @param key
     * @return
     */
    public Object getValueFromCacheMap(Object key){
        if (cacheMap.containsKey(key))
            return cacheMap.get(key);
        return "";
    }

    /**
     * 保存数据到传值缓存空间
     * @param key
     * @param value
     */
    public void putValueToIntentMap(Object key,Object value){
        intentMap.put(key,value);
    }

    /**
     * 保存缓存数据到临时缓存空间
     * @param key
     * @param value
     */
    public void putValueToCacheMap(Object key,Object value){
        cacheMap.put(key,value);
    }

    //    public void onTrimMemory(int level) {
//        Object[] callbacks = collectComponentCallbacks();
//        if (callbacks != null) {
//            for (int i=0; i<callbacks.length; i++) {
//                Object c = callbacks[i];
//                if (c instanceof ComponentCallbacks2) {
//                    ((ComponentCallbacks2)c).onTrimMemory(level);
//                }
//            }
//        }
//    }


    /**
     * 添加activity的回调管理
     */
    private void regActivityLifecycleCallbacks() {
        /**
         * activity的生命周期方法
         */
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                // 添加已激活的activity
                ActivityManager.getInstance().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityManager.getInstance().finishActivity(activity);
            }
        });
    }

    /**
     * 如果要像微信一样，所有字体都不允许随系统调节而发生大小变化，要怎么办呢？
     * 利用Android的Configuration类中的fontScale属性，其默认值为1，
     * 会随系统调节字体大小而发生变化，如果我们强制让其等于默认值，就可以实现字体不随调节改变，
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            //为了避免部分手机上Locale对象是空对象，在这里强制new一个Locale对象
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                Locale loc = Locale.getDefault();
                newConfig.setLocale(loc);
            }
            newConfig.setTo(newConfig);
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }
}
