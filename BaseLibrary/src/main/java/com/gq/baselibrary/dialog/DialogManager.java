package com.gq.baselibrary.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.gq.baselibrary.R;
/**
 * @Author Created by 王瑞铭
 * @Time   Created by 2018/9/25
 * @Document 弹框生成管理器
 */
public class DialogManager {

    /**
     * 一个页面只有一个对象
     */
    private AlertDialog mDialog;
    /**
     * 一个页面只有一个对象
     */
    private AlertDialog.Builder mBuilder;
    /**
     * 当前页面对象
     */
    private Context mContext;

    //内部全局唯一实例
    static DialogManager sInstance;

    //对外api
    public static DialogManager getInstance(Context context) {
        synchronized (DialogManager.class) {
            if (sInstance == null) {
                sInstance = new DialogManager(context.getApplicationContext());
            }
            return sInstance;
        }
    }

    private DialogManager(Context context){}

    /**
     * 采用默认样式创建弹框(屏蔽取消弹框功能)
     * @param context
     * @return
     */
    public AlertDialog createDialog(Context context) {
        return createDialog(context, R.style.DefaultDialogTheme,false, false);
    }

    /**
     * 屏蔽取消弹框功能
     * @param context
     * @param themId 弹框样式
     * @return
     */
    public AlertDialog createDialog(Context context,int themId) {
        return createDialog(context, themId,false, false);
    }

    /**
     * 点击物理返回键可以取消弹框
     * @param themId 弹框样式
     * @param context
     * @param isCanceledOutside 设置点击弹框外部是否可以取消弹框
     * @return
     */
    public AlertDialog createDialog(Context context,int themId, boolean isCanceledOutside) {
        return createDialog(context, themId,true, isCanceledOutside);
    }

    /**
     * 创建AlertDialog的实例，如果在当前页面已经创建了，那么直接复用
     * @param themId 弹框样式
     * @param context           上下文对象
     * @param isCancelable      物理返回键是否可以取消 false 不可以  true 可以
     * @param isCanceledOutside 点击外部是否可以取消  false 不可以  true 可以 （如果isCancelable = false，那么isCanceledOutside属性不起作用，实际效果相当于是false）
     * @return
     */
    public AlertDialog createDialog(Context context,int themId, boolean isCancelable, boolean isCanceledOutside) {
        if (!checkContext(context))
            reset();
        if (mDialog == null) {
            mDialog = build(context,themId).create();
        }
        mDialog.setCancelable(isCancelable);
        mDialog.setCanceledOnTouchOutside(isCanceledOutside);
        return mDialog;
    }

    private boolean checkContext(Context context) {
        return context == mContext;
    }

    private AlertDialog.Builder build(Context context,int themId) {
        AlertDialog.Builder builder = null;
        if (checkContext(context)) {
            //在同一个页面
            if (mBuilder != null) {
                builder = mBuilder;
            } else {
                //创建一个新的Builder
                builder = createBuilder(context,themId);
            }
        } else {
            //不是同一个页面
            //恢复最初始状态
            reset();
            mContext = context;
            //创建一个新的Builder
            builder = createBuilder(context,themId);
        }
        return builder;
    }

    private AlertDialog.Builder createBuilder(Context context,int themId) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context, themId);
        mBuilder = builder;
        return builder;
    }

    public void dismissDialog(){
        if (mDialog!=null&&mDialog.isShowing())
            mDialog.dismiss();
    }

    private void reset() {
        try {
            if (mDialog!=null&&mDialog.isShowing())
                mDialog.dismiss();
        }catch (Exception e){

        }finally {
            mDialog = null;
            mBuilder = null;
            mContext = null;
        }
    }

    public void destroy(Context context) {
        if (context != mContext) {
            context = null;
            return;
        }
        if (mDialog != null)
            mDialog.cancel();
        context = null;
        reset();
    }

}
