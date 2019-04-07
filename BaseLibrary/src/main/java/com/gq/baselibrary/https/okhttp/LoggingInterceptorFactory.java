package com.gq.baselibrary.https.okhttp;

import com.gq.baselibrary.https.Logger;

import okhttp3.logging.HttpLoggingInterceptor;

public class LoggingInterceptorFactory {

    public static HttpLoggingInterceptor getInterceptor(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Logger.e("网络：" + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }
}
