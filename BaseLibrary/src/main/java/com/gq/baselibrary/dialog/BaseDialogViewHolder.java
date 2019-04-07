package com.gq.baselibrary.dialog;

import android.util.SparseArray;
import android.view.View;

public class BaseDialogViewHolder {

    private View convertView;

    private SparseArray<View> mViews;

    public BaseDialogViewHolder(View view){
        convertView = view;
        mViews = new SparseArray<>();
    }

    public <V extends View> V getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (V) view;
    }

}
