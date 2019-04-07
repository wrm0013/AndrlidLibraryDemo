package com.gq.baselibrary.https.throwable;

import android.widget.Toast;

import com.gq.baselibrary.https.HttpManager;

/**
 * @Author Created by 王瑞铭
 * @Time   Created by 2018/9/25
 * @Document API异常统一管理类
 */
public class APIThrowable {

    public APIThrowable(String code,String message){
        switch (code){
            case "503":
                //服务升级
                break;
            case "999":
                //Token失效
                break;
            case "99999":
                //没有网络
                Toast.makeText(HttpManager.getInstance().getContext(),message,Toast.LENGTH_LONG).show();
                break;
        }
    }

}
