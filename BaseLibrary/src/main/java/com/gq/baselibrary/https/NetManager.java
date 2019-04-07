package com.gq.baselibrary.https;

import com.gq.baselibrary.https.throwable.APIThrowable;
import com.gq.baselibrary.util.NetUtil;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author Created by 王瑞铭
 * @Time   Created by 2018/9/20
 * @Document 进行网络请求的管理器
 */
public class NetManager<T> {

    private static class SingleNetManager{
        private static final NetManager INSTANCE = new NetManager();
    }

    public static NetManager getInstance(){
        return SingleNetManager.INSTANCE;
    }

    public <T> void post(Observable<T> observable, Observer<T> observer){
        if (!NetUtil.isNetConnected()){
            new APIThrowable("99999","网络异常，请检查网络连接是否正常！");
            return;
        }

        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
