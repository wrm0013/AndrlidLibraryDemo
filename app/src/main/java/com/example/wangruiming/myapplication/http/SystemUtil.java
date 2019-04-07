package com.example.wangruiming.myapplication.http;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.gq.baselibrary.https.HttpManager;
import com.gq.lib.permissionlib2.annotation.ApplyPermission;

public class SystemUtil {

    /**
     * getAppVersion
     * 获取版本号
     *
     * @return
     */
    public static final String getAppVersion() {
        String appVersion = null;
        PackageManager manager = HttpManager.getInstance().getContext().getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(HttpManager.getInstance().getContext().getPackageName(), 0);
            appVersion = info.versionName; // 版本名
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appVersion;
    }

    //获取设备唯一标识
//    @ApplyPermission(value = {Manifest.permission.READ_PHONE_STATE},requestCode = 0)
    public static String getDeviceId() {
        Context context = HttpManager.getInstance().getContext();
        String id = "";
//        TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        id = telManager.getDeviceId();
//        if (TextUtils.isEmpty(id))
//            id = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
        return id;
    }

}
