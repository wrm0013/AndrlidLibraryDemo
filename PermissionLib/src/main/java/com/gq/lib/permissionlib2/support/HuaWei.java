package com.gq.lib.permissionlib2.support;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.gq.lib.permissionlib2.interf.ISetting;

public class HuaWei implements ISetting {
    private Context context;

    public HuaWei(Context context) {
        this.context = context;
    }

    @Override
    public Intent getSetting() {
        Intent intent = new Intent(context.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");
        intent.setComponent(comp);
        return intent;
    }
}
