package com.gq.baselibrary.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by user on 2017/12/14.
 */

public abstract class CommonBaseAdapter<D> extends BaseAdapter {

    public abstract int setLayout();

    public abstract void convertData(CommonBaseHolder holder,int position, D item);

    private List<D> mList;

    public Context mContext;

    public CommonBaseAdapter(Context context, List<D> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public D getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonBaseHolder holder = CommonBaseHolder.newInstance(mContext, convertView, parent, setLayout());
        View mConvertView = holder.getConvertView();
        convertData(holder,position, mList.get(position));
        return mConvertView;
    }
}
