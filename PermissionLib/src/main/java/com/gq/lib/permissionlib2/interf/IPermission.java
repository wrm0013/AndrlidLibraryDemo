package com.gq.lib.permissionlib2.interf;

import java.util.List;


public interface IPermission {

    //同意权限
    void PermissionGranted();

    //取消权限
    void PermissionCanceled(int requestCode);
}
