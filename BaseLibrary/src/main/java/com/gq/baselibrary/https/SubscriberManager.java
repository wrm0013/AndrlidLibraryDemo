package com.gq.baselibrary.https;

import android.support.v4.util.ArrayMap;

import org.reactivestreams.Subscriber;

import java.util.Set;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by user on 2018/1/12.
 */

public class SubscriberManager implements SubscriberManagerListener {
    //请求集合
    private ArrayMap<String, Disposable> manager;

    private SubscriberManager() {
        manager = new ArrayMap<>();
    }

    @Override
    public void addSubscriber(String tag, Disposable subscription) {
        if (manager != null)
            manager.put(tag, subscription);
    }

    @Override
    public void removeSubscriber(String tag) {
        if (manager != null && !manager.isEmpty())
            if (manager.containsKey(tag))
                manager.remove(tag);
    }

    @Override
    public void removeAllSubscriber() {
        if (manager != null && !manager.isEmpty())
            manager.clear();
    }

    @Override
    public void cancelSubscriber(String tag) {
        if (manager == null || manager.isEmpty())
            return;
        if (manager.get(tag) == null)
            return;
        if (manager.get(tag).isDisposed())
            return;
        manager.get(tag).dispose();
        manager.remove(tag);
    }

    @Override
    public void cancelAllSubscriber() {
        if (manager == null || manager.isEmpty())
            return;
        Set<String> keys = manager.keySet();
        for (String key : keys) {
            cancelSubscriber(key);
        }
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final SubscriberManager INSTANCE = new SubscriberManager();
    }

    //获取单例
    public static SubscriberManager getInstance() {
        return SubscriberManager.SingletonHolder.INSTANCE;
    }


}
