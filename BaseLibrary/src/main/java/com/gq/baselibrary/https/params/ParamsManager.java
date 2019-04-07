package com.gq.baselibrary.https.params;

import android.os.Build;

import com.gq.baselibrary.https.HttpManager;
import com.gq.baselibrary.https.httputil.SafeUtils;
import com.gq.baselibrary.https.httputil.SignUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Created by 王瑞铭
 * @Time   Created by 2018/9/20
 * @Document 处理参数
 */
public class ParamsManager {

    private static class SingleParamsManager{
        private static final ParamsManager INSTANCE = new ParamsManager();
    }

    public static ParamsManager getInstance(){
        return SingleParamsManager.INSTANCE;
    }

    public Map encryptParams(Map map,String serviceUrl){
        Map params;
        if (null == map)
            map = new HashMap();
        params = map;
        params.put("ver", HttpManager.getInstance().getVersionCode());
        params.put("tokenId", HttpManager.getInstance().getToken());
        params.put("mobile", HttpManager.getInstance().getMobile());
        params.put("device", "android");
        params.put("deviceId", HttpManager.getInstance().getDeviceID());
        String s = params.toString().replace(", ", "&").replace("{", "").replace("}", "");
        String sign = SafeUtils.sequenceUrl(serviceUrl + "?" + s);
        params.put("sign", SignUtil.SignMD5(sign));
        params.put("appModel", Build.MODEL.replace(" ", "").replace("#", "").replace("*", "").replace("-", "").trim());
        params.put("mobileSysVer", Build.VERSION.RELEASE);
        return params;
    }

}
