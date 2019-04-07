package com.gq.baselibrary.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by user on 2017/12/14.
 */

public class CommonBaseHolder {

    private View convertView;

    private SparseArray<View> mViews;

    private Context mContext;

    public CommonBaseHolder(Context context, ViewGroup parent, int layoutId) {
        this.mContext = context;
        convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        convertView.setTag(this);
        mViews = new SparseArray<>();
    }

    public static CommonBaseHolder newInstance(Context context, View convertView, ViewGroup parent, int layoutId) {
        if (convertView == null) {
            return new CommonBaseHolder(context, parent, layoutId);
        } else {
            return (CommonBaseHolder) convertView.getTag();
        }
    }

    public View getConvertView() {
        return convertView;
    }

    public <V extends View> V getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (V) view;
    }

    /**
     *
     * @param viewId 控件id
     * @param value 字符串
     */
    public void setText(int viewId, String value) {
        TextView tv = getView(viewId);
        tv.setText(TextUtils.isEmpty(value) ? "" : value);
    }

    /**
     *
     * @param viewId
     * @param value
     */
    public void setText(int viewId, CharSequence value) {
        TextView tv = getView(viewId);
        tv.setText(TextUtils.isEmpty(value) ? "" : value);
    }

    /**
     *
     * @param viewId
     * @param resId 资源id
     */
    public void setText(int viewId,int resId){
        TextView tv = getView(viewId);
        tv.setText(resId);
    }

    /**
     *
     * @param viewId
     * @param url 图片地址
     * @param errorRes 网络图片加载失败之后显示的图片资源id
     */
    public void setImage(int viewId, String url, int errorRes) {
        ImageView imageView = getView(viewId);
        Glide.with(mContext).load(url).error(errorRes).into(imageView);
    }

    /**
     *
     * @param viewId
     * @param url 图片地址
     */
    public void setImage(int viewId, String url) {
        ImageView imageView = getView(viewId);
        Glide.with(mContext).load(url).into(imageView);
    }

    /**
     *
     * @param viewId
     * @param resId 图片资源id
     */
    public void setImage(int viewId,int resId){
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resId);
    }

}
