package com.gq.lib.permissionlib2.support;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import com.gq.lib.permissionlib2.interf.ISetting;

import java.util.List;


/**
 * Created by mq on 2018/3/6 上午11:44
 * mqcoder90@gmail.com
 */

public class Meizu implements ISetting {

    private Context context;

    public Meizu(Context context) {
        this.context = context;
    }

    @Override
    public Intent getSetting() {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra("packageName", context.getPackageName());
        return intent;
    }


}
