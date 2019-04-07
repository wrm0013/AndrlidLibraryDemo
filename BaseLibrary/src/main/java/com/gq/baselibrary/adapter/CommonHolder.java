package com.gq.baselibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gq.baselibrary.imageloader.ImageLoader;


public class CommonHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;

    private Context mContext;

    private View convertView;

    public CommonHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        this.convertView = itemView;
        this.mViews = new SparseArray<>();
    }

    public static CommonHolder get(Context context, ViewGroup parent, int layoutId) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new CommonHolder(context, view);
    }

    public <V extends View> V getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (V) view;
    }

    public void setText(int viewId,String value){
        TextView tv = getView(viewId);
        tv.setText(value);
    }

    public void setImage(int viewId,String url,int errorRes){
        ImageView imageView = getView(viewId);
        ImageLoader.loadImageHolderError(mContext,imageView,url,errorRes,errorRes);
    }

}