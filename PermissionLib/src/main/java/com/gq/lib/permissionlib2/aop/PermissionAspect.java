package com.gq.lib.permissionlib2.aop;

import android.app.Fragment;
import android.content.Context;
import android.util.Log;

import com.gq.lib.permissionlib2.PermissionRequestActivity;
import com.gq.lib.permissionlib2.annotation.ApplyPermission;
import com.gq.lib.permissionlib2.annotation.CanclePermission;
import com.gq.lib.permissionlib2.bean.CancelBean;
import com.gq.lib.permissionlib2.bean.DenyBean;
import com.gq.lib.permissionlib2.interf.IPermission;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


@Aspect
public class PermissionAspect {

    private static final String PERMISSION_REQUEST_POINTCUT =
            "execution(@com.gq.lib.permissionlib2.annotation.ApplyPermission * *(..))";

    @Pointcut(PERMISSION_REQUEST_POINTCUT + " && @annotation(applyPermission)")
    public void requestPermissionMethod(ApplyPermission applyPermission) {
        Log.e("TAG","TAG");
    }

    @Around("requestPermissionMethod(applyPermission)")
    public void AroundJoinPoint(final ProceedingJoinPoint joinPoint, ApplyPermission applyPermission) {

        Context context = null;
        final Object object = joinPoint.getThis();
        if (object instanceof Context) {
            context = (Context) object;
        } else if (object instanceof Fragment) {
            context = ((Fragment) object).getActivity();
        } else if (object instanceof android.support.v4.app.Fragment) {
            context = ((android.support.v4.app.Fragment) object).getActivity();
        }
        if (context == null || applyPermission == null) return;

        PermissionRequestActivity.PermissionRequest(context, applyPermission.value(),
                applyPermission.requestCode(), new IPermission() {
                    @Override
                    public void PermissionGranted() {
                        try {
                            joinPoint.proceed();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }

                    @Override
                    public void PermissionCanceled(int requestCode) {
                        Class<?> cls = object.getClass();
                        Method[] methods = cls.getDeclaredMethods();
                        if (methods == null || methods.length == 0) return;
                        for (Method method : methods) {
                            //过滤不含自定义注解PermissionCanceled的方法
                            boolean isHasAnnotation = method.isAnnotationPresent(CanclePermission.class);
                            if (isHasAnnotation) {
                                method.setAccessible(true);
                                //获取方法类型
                                Class<?>[] types = method.getParameterTypes();
                                if (types == null || types.length != 1) return;
                                //获取方法上的注解
                                CanclePermission aInfo = method.getAnnotation(CanclePermission.class);
                                if (aInfo == null) return;
                                //解析注解上对应的信息
                                CancelBean bean = new CancelBean();
                                bean.setRequestCode(requestCode);
                                try {
                                    method.invoke(object, bean);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
    }

}
