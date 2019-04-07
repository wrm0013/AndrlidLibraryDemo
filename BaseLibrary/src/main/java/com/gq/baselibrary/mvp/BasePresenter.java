package com.gq.baselibrary.mvp;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends BaseView> {

    protected WeakReference<V> mViewRef;

    public BasePresenter(V view) {
        attachView(view);
    }

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    /**
     * 获取view的方法
     *
     * @return 当前关联的view
     */
    public V getView() {
        return mViewRef.get();
    }

}
