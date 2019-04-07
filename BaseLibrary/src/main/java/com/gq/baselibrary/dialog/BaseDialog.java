package com.gq.baselibrary.dialog;


import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.gq.baselibrary.R;
import com.gq.baselibrary.https.HttpManager;
import com.gq.baselibrary.manager.ActivityManager;

public abstract class BaseDialog {

    public abstract void convertDialog(AlertDialog dialog,BaseDialogViewHolder holder);

    private DialogManager manager;

    private Context mContext;

    public BaseDialog(int layoutId){
        mContext = ActivityManager.getInstance().currentActivity();
        manager = DialogManager.getInstance(mContext);
        AlertDialog dialog = manager.createDialog(mContext);
        dialog.show();
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_layout, null);
        BaseDialogViewHolder holder = new BaseDialogViewHolder(view);
        convertDialog(dialog,holder);
        Window window = dialog.getWindow();
        window.setContentView(view);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;//如果不设置,可能部分机型出现左右有空隙,也就是产生margin的感觉
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED;//显示dialog的时候,就显示软键盘
        params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;//就是这个属性导致不能获取焦点,默认的是FLAG_NOT_FOCUSABLE,故名思义不能获取输入焦点,
        params.dimAmount = 0.5f;//设置对话框的透明程度背景(非布局的透明度)
        window.setAttributes(params);
    }

}
