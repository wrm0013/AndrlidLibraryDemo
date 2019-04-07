package com.gq.baselibrary.https.response;

import android.widget.TextView;

import com.google.gson.Gson;
import com.gq.baselibrary.R;
import com.gq.baselibrary.dialog.BaseDialogViewHolder;
import com.gq.baselibrary.https.HttpManager;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class EntityCallBack<T> extends BaseObserver<String> {

    public EntityCallBack(String tag) {
        super(tag);
    }

    public EntityCallBack(String tag, boolean isShow) {
        super(tag, isShow);
    }

    protected abstract void onSuccess(T entity);

    @Override
    public int createLayout() {
        return R.layout.dialog_layout;
    }

    @Override
    public void convert(BaseDialogViewHolder holder) {
        ((TextView)(holder.getView(R.id.dialog_tv))).setText("Hahahahhahahaah");
    }

    @Override
    public void onResponse(String response) {
        ParameterizedType type = null;
        try {
            type = (ParameterizedType) this.getClass().getGenericSuperclass();
            Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
            HttpResult result = new Gson().fromJson(response,typeArgument);
            onSuccess((T) result);
        }catch (Exception e){

        }
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
