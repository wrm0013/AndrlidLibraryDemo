package com.gq.baselibrary.https.response;

import com.gq.baselibrary.dialog.BaseDialogViewHolder;

public abstract class StringCallBack extends BaseObserver<String> {

    protected abstract void onSuccess(String entity);

    public StringCallBack(String tag) {
        super(tag);
    }

    public StringCallBack(String tag, boolean isShow) {
        super(tag, isShow);
    }

    @Override
    public int createLayout() {
        return 0;
    }

    @Override
    public void convert(BaseDialogViewHolder holder) {

    }

    @Override
    public void onResponse(String response) {
        onSuccess(response);
    }

    @Override
    public void onFailure(String code, String msg, String response) {

    }

    @Override
    public void onErrorCompleted(Throwable e) {

    }

    @Override
    public void onCompleted() {

    }
}
