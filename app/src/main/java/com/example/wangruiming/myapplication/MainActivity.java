package com.example.wangruiming.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.gq.baselibrary.base.BaseActivity;
import com.gq.baselibrary.https.HttpManager;
import com.gq.lib.permissionlib2.annotation.ApplyPermission;
import com.gq.lib.permissionlib2.annotation.CanclePermission;
import com.gq.lib.permissionlib2.bean.CancelBean;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
//        getDeviceId();
        Map map = new HashMap();
        map.put("id", "6");
        map.put("width", ScreenUtil.getScreenWidth(this));
        mPresenter.getList(map);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    //获取设备唯一标识
    @SuppressLint("MissingPermission")
    @ApplyPermission(value = {Manifest.permission.CAMERA},requestCode = 0)
    public String getDeviceId() {
        Toast.makeText(this,"获取权限成功",Toast.LENGTH_LONG).show();
//        Context context = HttpManager.getInstance().getContext();
        String id = "11";
//        TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        id = telManager.getDeviceId();
//        if (TextUtils.isEmpty(id))
//            id = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
        return id;
    }
    @CanclePermission
    public String getDeviceId1(CancelBean bean) {
        Toast.makeText(this,"获取权限成功11111",Toast.LENGTH_LONG).show();
//        Context context = HttpManager.getInstance().getContext();
        String id = "11";
//        TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        id = telManager.getDeviceId();
//        if (TextUtils.isEmpty(id))
//            id = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
        return id;
    }
}
