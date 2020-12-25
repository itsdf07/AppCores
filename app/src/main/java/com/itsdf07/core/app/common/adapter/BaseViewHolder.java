package com.itsdf07.core.app.common.adapter;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Description:
 * @Author Aso
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/12/03
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding binding = null;
    private SparseArray<View> views;
    private String[] s;

    public BaseViewHolder(ViewDataBinding itemView) {
        super(itemView.getRoot());
        this.binding = itemView;
        this.views = new SparseArray<>();
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public View getRootView() {
        return itemView;
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
