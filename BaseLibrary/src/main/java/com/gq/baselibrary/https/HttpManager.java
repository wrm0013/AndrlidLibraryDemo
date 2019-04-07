package com.gq.baselibrary.https;

import android.app.Application;
import android.content.Context;

import com.gq.baselibrary.https.factory.ResponseConvertFactory;
import com.gq.baselibrary.https.okhttp.LoggingInterceptorFactory;
import com.gq.baselibrary.https.okhttp.SSLFactory;
import com.gq.baselibrary.https.params.ParamsManager;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.http.PUT;

/**
 * @Author Created by 王瑞铭
 * @Time   Created by 2018/9/19
 * @Document 网络管理类
 */
public class HttpManager {

    private Application mContext;

    private String BASE_URL;

    /**
     * 是否是调试模式
     */
    public boolean DEBUG = true;
    /**
     * 连接超时
     */
    private long CONNECT_TIME_OUT = 6000;
    /**
     * 读入超时
     */
    private long READ_TIME_OUT = 6000;
    /**
     * 写入超时
     */
    private long WRITE_TIME_OUT = 6000;

    private String versionCode = "1.0";

    private String mobile = "";

    private String token = "";

    private String deviceID = "";

    private OkHttpClient.Builder okHttpBuilder;

    private Retrofit.Builder retrofitBuilder;

    private static class SingleInstance{
        private static final HttpManager INSTANCE = new HttpManager();
    }

    /**
     * 单例
     * @return
     */
    public static HttpManager getInstance(){
        return SingleInstance.INSTANCE;
    }

    public void initialize(Application context){
        this.mContext = context;
        okHttpBuilder = new OkHttpClient.Builder();
        retrofitBuilder = new Retrofit.Builder();
    }

    public void init(){
        okHttpBuilder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS);
        okHttpBuilder.readTimeout(READ_TIME_OUT,TimeUnit.MILLISECONDS);
        okHttpBuilder.writeTimeout(WRITE_TIME_OUT,TimeUnit.MILLISECONDS);
        retrofitBuilder.addConverterFactory(ResponseConvertFactory.create());
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        retrofitBuilder.baseUrl(BASE_URL);
    }

    public Context getContext(){
        if (mContext == null)
            throw new ExceptionInInitializerError("请先在全局Application中调用 HttpManager.getInstance().init() 初始化！");
        return mContext;
    }

    /**
     * 设置调试模式
     * @param debug
     * @return
     */
    public HttpManager setDebug(boolean debug){
        this.DEBUG = debug;
        return this;
    }

    /**
     * 设置请求地址
     * @param baseUrl
     * @return
     */
    public HttpManager setBaseUrl(String baseUrl){
        this.BASE_URL = baseUrl;
        return this;
    }


    /**
     * 设置连接超时
     * @param connectTimeOut
     * @return
     */
    public HttpManager setConnectTimeOut(long connectTimeOut){
        this.CONNECT_TIME_OUT = connectTimeOut;
        return this;
    }

    public HttpManager setReadTimeOut(long readTimeOut){
        this.READ_TIME_OUT = readTimeOut;
        return this;
    }

    public HttpManager setWriteTimeOut(long writeTimeOut){
        this.WRITE_TIME_OUT = writeTimeOut;
        return this;
    }

    /**
     * 设置日志拦截器
     * @return
     */
    public HttpManager setLoggingInterceptor(){
        HttpLoggingInterceptor loggingInterceptor = LoggingInterceptorFactory.getInterceptor();
        okHttpBuilder.addInterceptor(loggingInterceptor).retryOnConnectionFailure(false).addNetworkInterceptor(loggingInterceptor);
        return this;
    }

    /**
     * 设置证书
     * @return
     */
    public HttpManager setSSLSocket(){
        okHttpBuilder.sslSocketFactory(SSLFactory.getSSL());
        return this;
    }

    /**
     * 获取OkHttpClient
     * @return
     */
    public OkHttpClient getOkHttpClient(){
        return okHttpBuilder.build();
    }

    /**
     * 获取OkHttpClient构造器
     * @return
     */
    public OkHttpClient.Builder getOkHttpBuilder(){
        return okHttpBuilder;
    }

    public Retrofit.Builder getRetrofitBuilder(){
        return retrofitBuilder;
    }

    public Retrofit getRetrofit(){
        return retrofitBuilder.build();
    }

    /**
     * 设置版本号
     * @param version
     */
    public HttpManager setVersionCode(String version){
        this.versionCode = version;
        return this;
    }

    public String getVersionCode() {
        return versionCode;
    }

    /**
     * 设置手机号
     * @param mobile
     */
    public HttpManager setMobile(String mobile){
        this.mobile = mobile;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    /**
     * 设置token
     * @param token
     */
    public HttpManager setToken(String token){
        this.token = token;
        return this;
    }

    public String getToken() {
        return token;
    }

    /**
     * 设置设置唯一识别码
     * @param deviceID
     */
    public HttpManager setDeviceID(String deviceID){
        this.deviceID = deviceID;
        return this;
    }

    public String getDeviceID() {
        return deviceID;
    }

    /**
     * 获取加密之后的参数
     * @param params
     * @param method
     * @return
     */
    public Map getParams(Map params,String method){
        return ParamsManager.getInstance().encryptParams(params,BASE_URL+method);
    }

}
