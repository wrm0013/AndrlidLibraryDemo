package com.gq.baselibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonHolder> {

    public abstract int setLayout();

    public abstract void convertData(CommonHolder holder, int position,T entity);

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public interface OnItemLongClickListener {
        void onLongClick(int position);
    }

    public interface OnLoadMoreListener{
        void onLoadMore();
    }

    private OnItemClickListener clickListener;

    private OnItemLongClickListener longClickListener;

    public OnLoadMoreListener loadMoreListener;

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    private List<T> mList;

    private Context mContext;

    private LayoutInflater mInflater;

    public CommonAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.mList = list;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public CommonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonHolder.get(mContext,parent,setLayout());
    }

    @Override
    public void onBindViewHolder(CommonHolder holder, int position) {
        if (null != clickListener)
            clickListener.onClick(position);
        if (null != longClickListener)
            longClickListener.onLongClick(position);
        if (null!=loadMoreListener){
            if (position == getItemCount()-1)
                loadMoreListener.onLoadMore();
        }
        convertData(holder, position,mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}