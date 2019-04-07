package com.gq.baselibrary.https;


import io.reactivex.disposables.Disposable;

/**
 * Created by user on 2018/1/12.
 * 接口请求的管理类
 */

public interface SubscriberManagerListener {

    //添加
    void addSubscriber(String tag, Disposable observer);
    //移除
    void removeSubscriber(String tag);
    //移除所有
    void removeAllSubscriber();
    //取消
    void cancelSubscriber(String tag);
    //取消所有
    void cancelAllSubscriber();
}
