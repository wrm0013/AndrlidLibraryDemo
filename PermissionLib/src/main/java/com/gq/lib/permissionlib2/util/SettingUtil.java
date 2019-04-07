package com.gq.lib.permissionlib2.util;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.gq.lib.permissionlib2.interf.ISetting;
import com.gq.lib.permissionlib2.support.Default;
import com.gq.lib.permissionlib2.support.HuaWei;
import com.gq.lib.permissionlib2.support.Meizu;
import com.gq.lib.permissionlib2.support.OPPO;
import com.gq.lib.permissionlib2.support.Vivo;
import com.gq.lib.permissionlib2.support.XiaoMi;


public class SettingUtil {

    private static final String MANUFACTURER_HUAWEI = "HUAWEI";//华为
    private static final String MANUFACTURER_MEIZU = "Meizu";//魅族
    private static final String MANUFACTURER_XIAOMI = "Xiaomi";//小米
    private static final String MANUFACTURER_SONY = "Sony";//索尼
    private static final String MANUFACTURER_OPPO = "OPPO";
    private static final String MANUFACTURER_LG = "LG";
    private static final String MANUFACTURER_VIVO = "vivo";
    private static final String MANUFACTURER_SAMSUNG = "samsung";//三星
    private static final String MANUFACTURER_LETV = "Letv";//乐视
    private static final String MANUFACTURER_ZTE = "ZTE";//中兴
    private static final String MANUFACTURER_YULONG = "YuLong";//酷派
    private static final String MANUFACTURER_LENOVO = "LENOVO";//联想

    /**
     * 跳设置界面
     *
     * @param context context
     */
    public static void go2Setting(Context context) {
        ISetting iSetting;
        Log.e("TAG", Build.MANUFACTURER);
        switch (Build.MANUFACTURER) {
            case MANUFACTURER_VIVO:
                iSetting = new Vivo(context);
                break;
            case MANUFACTURER_OPPO:
                iSetting = new OPPO(context);
                break;
            case MANUFACTURER_HUAWEI:
                iSetting = new HuaWei(context);
                break;
            case MANUFACTURER_MEIZU:
                iSetting = new Meizu(context);
                break;
            case MANUFACTURER_XIAOMI:
                iSetting = new XiaoMi(context);
                break;
            default:
                iSetting = new Default(context);
                break;
        }
        if (iSetting.getSetting() == null) return;
        context.startActivity(iSetting.getSetting());
    }


}
