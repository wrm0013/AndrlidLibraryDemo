package com.example.wangruiming.myapplication;

import com.example.wangruiming.myapplication.http.ADListEntity;
import com.gq.baselibrary.https.HttpManager;
import com.gq.baselibrary.mvp.BaseModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class DefaultModel extends BaseModel<ApiService> {

    @Override
    public ApiService createService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    public Observable<ADListEntity> getList(Map param, String method){
        return service.getAdList(HttpManager.getInstance().getParams(param,method));
    }
}
