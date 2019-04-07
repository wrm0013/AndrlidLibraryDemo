package com.gq.baselibrary.https.response;

import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import com.gq.baselibrary.R;
import com.gq.baselibrary.dialog.BaseDialog;
import com.gq.baselibrary.dialog.BaseDialogViewHolder;
import com.gq.baselibrary.https.HttpManager;
import com.gq.baselibrary.https.SubscriberManager;
import com.gq.baselibrary.https.throwable.APIThrowable;
import com.gq.baselibrary.https.throwable.HttpThrowable;
import com.gq.baselibrary.util.NetUtil;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<String> {

    private AlertDialog mDialog;

    private String tag;

    private boolean isShowDialog;

    public BaseObserver(String tag) {
        this(tag,true);
    }

    public BaseObserver(String tag,boolean isShow) {
        this.tag = tag;
        this.isShowDialog = isShow;
    }

    public void showDialog(){
        if (NetUtil.isNetConnected()&& isShowDialog) {
            new BaseDialog(createLayout() == 0 ? R.layout.dialog_layout : createLayout()) {
                @Override
                public void convertDialog(AlertDialog dialog, BaseDialogViewHolder holder) {
                    mDialog = dialog;
                    convert(holder);
                }
            };
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        SubscriberManager.getInstance().addSubscriber(tag,d);
    }

    @Override
    public void onNext(String s) {
        try {
            JSONObject o = new JSONObject(s);
            String code = o.optString("code");
            if (code.equals("0000")) {
                onResponse(s);
            } else {
                onFailure(code,o.optString("msg"),s);
                //异常处理
                new APIThrowable(code,o.optString("msg"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            SubscriberManager.getInstance().cancelSubscriber(tag);
            if (mDialog != null&& isShowDialog)
                mDialog.dismiss();
        }
    }

    @Override
    public void onError(Throwable e) {
        new HttpThrowable(e);
        onErrorCompleted(e);
        if (mDialog != null&& isShowDialog)
            mDialog.dismiss();
        SubscriberManager.getInstance().cancelSubscriber(tag);
    }

    @Override
    public void onComplete() {
        onCompleted();
    }

    public abstract int createLayout();

    public abstract void convert(BaseDialogViewHolder holder);

    public abstract void onResponse(String response);

    public abstract void onFailure(String code,String msg,String response);

    public abstract void onErrorCompleted(Throwable e);

    public abstract void onCompleted();
}
