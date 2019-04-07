package com.gq.baselibrary.https;

import android.util.Log;

public class Logger {

    private static final String TAG = "Logger";

    public static void v(String message){
        if (HttpManager.getInstance().DEBUG)
            Log.v(TAG,message);
    }

    public static void v(String tag,String message){
        if (HttpManager.getInstance().DEBUG)
            Log.v(tag,message);
    }

    public static void d(String message){
        if (HttpManager.getInstance().DEBUG)
            Log.d(TAG,message);
    }

    public static void d(String tag,String message){
        if (HttpManager.getInstance().DEBUG)
            Log.d(tag,message);
    }

    public static void i(String message){
        if (HttpManager.getInstance().DEBUG)
            Log.i(TAG,message);
    }

    public static void i(String tag,String message){
        if (HttpManager.getInstance().DEBUG)
            Log.i(tag,message);
    }

    public static void w(String message){
        if (HttpManager.getInstance().DEBUG)
            Log.w(TAG,message);
    }

    public static void w(String tag,String message){
        if (HttpManager.getInstance().DEBUG)
            Log.w(tag,message);
    }

    public static void e(String message){
        if (HttpManager.getInstance().DEBUG)
            Log.e(TAG,message);
    }

    public static void e(String tag,String message){
        if (HttpManager.getInstance().DEBUG)
            Log.e(tag,message);
    }

}
