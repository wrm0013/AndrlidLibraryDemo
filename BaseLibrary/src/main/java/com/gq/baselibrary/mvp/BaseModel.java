package com.gq.baselibrary.mvp;

import com.gq.baselibrary.https.HttpManager;

import retrofit2.Retrofit;

public abstract class BaseModel<T> {

    public T service;

    public BaseModel(){
        service = createService(HttpManager.getInstance().getRetrofit());
    }

    public abstract T createService(Retrofit retrofit);

}
