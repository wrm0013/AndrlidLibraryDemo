package com.gq.baselibrary.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gq.baselibrary.dialog.DialogManager;
import com.gq.baselibrary.mvp.BasePresenter;

public abstract class BaseActivity<T extends BasePresenter> extends Activity {

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    /**
     * 用于创建Presenter和判断是否使用MVP模式(由子类实现)
     * @return
     */
    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        DialogManager.getInstance(this).destroy(this);
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
