package com.example.wangruiming.myapplication;


import com.example.wangruiming.myapplication.http.ServiceConfig;
import com.example.wangruiming.myapplication.http.SystemUtil;
import com.gq.baselibrary.base.BaseApplication;
import com.gq.baselibrary.https.HttpManager;

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        initialize(this);
        super.onCreate();
    }

    @Override
    protected void initConfig() {
        //初始化网络框架
        HttpManager.getInstance()
                .setDebug(true)
                .setBaseUrl(ServiceConfig.getService())
                .setLoggingInterceptor()
                .setSSLSocket()
                .setToken("")
                .setVersionCode("4.8.1")
                .setMobile("")
                .setDeviceID(SystemUtil.getDeviceId())
                .init();
    }
}
