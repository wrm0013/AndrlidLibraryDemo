package com.example.wangruiming.myapplication;

import com.example.wangruiming.myapplication.http.ADListEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("adList")
    Observable<ADListEntity> getAdList(@FieldMap Map<String, Object> map);
}
