package com.example.wangruiming.myapplication;

import com.example.wangruiming.myapplication.http.ADListEntity;
import com.gq.baselibrary.https.Logger;
import com.gq.baselibrary.https.NetManager;
import com.gq.baselibrary.https.SubscriberManager;
import com.gq.baselibrary.https.response.EntityCallBack;
import com.gq.baselibrary.https.response.HttpResult;
import com.gq.baselibrary.https.response.StringCallBack;
import com.gq.baselibrary.mvp.BasePresenter;

import java.util.Map;

public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView view) {
        super(view);
    }

    public void getList(Map param){
        DefaultModel model = new DefaultModel();
//        NetManager.getInstance().post(model.getList(param, "adList"), new StringCallBack() {
//            @Override
//            protected void onSuccess(String entity) {
//                Logger.d("");
//            }
//
//            @Override
//            protected void onFailure(Throwable e) {
//                Logger.d("");
//            }
//        });

        NetManager.getInstance().post(model.getList(param, "adList"),
                new EntityCallBack<HttpResult<ADListEntity>>("adList",false) {

            @Override
            protected void onSuccess(HttpResult<ADListEntity> entity) {
                Logger.e(entity.getCode());
            }

        });
    }

}
