package com.gq.baselibrary.https.observer;
/**
 * @Author Created by 王瑞铭
 * @Time   Created by 2018/10/12
 * @Document 观察者
 */
public abstract class GQStateOvserver {

    /**
     * 网络连接成功
     */
    public void onConnected(){

    }

    /**
     * 无网状态
     */
    public void onUnConnected(){

    }

    /**
     * token失效
     */
    public void tokenInvalid(){

    }

    /**
     * 服务停机
     */
    public void serviceStop(){

    }

}
