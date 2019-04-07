package com.gq.lib.permissionlib2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.gq.lib.permissionlib2.interf.IPermission;
import com.gq.lib.permissionlib2.util.PermissionUtil;
import com.gq.lib.permissionlib2.util.SettingUtil;

public class PermissionRequestActivity extends Activity {

    private static IPermission permissionListener;
    private String[] permissions;
    private static final String PERMISSION_KEY = "permission_key";
    private static final String REQUEST_CODE = "request_code";
    private int requestCode;
    private static Context mContext;

    /**
     * 跳转到Activity申请权限
     *
     * @param context     Context
     * @param permissions Permission List
     * @param iPermission Interface
     */
    public static void PermissionRequest(Context context, String[] permissions, int requestCode, IPermission iPermission) {
        mContext = context;
        permissionListener = iPermission;
        Intent intent = new Intent(context, PermissionRequestActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putStringArray(PERMISSION_KEY, permissions);
        bundle.putInt(REQUEST_CODE, requestCode);
        intent.putExtras(bundle);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(0, 0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_permission);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            permissions = bundle.getStringArray(PERMISSION_KEY);
            requestCode = bundle.getInt(REQUEST_CODE, 0);
        }
        if (permissions == null || permissions.length <= 0) {
            finish();
            return;
        }
        requestPermission(permissions);
    }


    /**
     * 申请权限
     *
     * @param permissions permission list
     */
    private void requestPermission(String[] permissions) {

        if (PermissionUtil.hasSelfPermissions(this, permissions)) {
            //all permissions granted
            if (permissionListener != null) {
                permissionListener.PermissionGranted();
                permissionListener = null;
            }
            finish();
            overridePendingTransition(0, 0);
        } else {
            //request permissions
            ActivityCompat.requestPermissions(this, permissions, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (PermissionUtil.verifyPermissions(grantResults)) {
            //所有权限都同意
            if (permissionListener != null) {
                permissionListener.PermissionGranted();
            }
        } else {
            if (!PermissionUtil.shouldShowRequestPermissionRationale(this, permissions)) {
                //权限被拒绝并且选中不再提示
                new AlertDialog.Builder(mContext)
                        .setTitle("提示")
                        .setMessage("权限被禁止，需要手动打开")
                        .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                SettingUtil.go2Setting(mContext);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                //权限被取消
                if (permissionListener != null) {
                    permissionListener.PermissionCanceled(requestCode);
                }
            }

        }
        permissionListener = null;
        finish();
        overridePendingTransition(0, 0);
    }

}
