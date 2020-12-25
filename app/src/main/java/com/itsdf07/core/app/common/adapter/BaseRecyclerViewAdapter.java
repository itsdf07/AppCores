package com.itsdf07.core.app.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @Description:
 * @Author Aso
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/12/03
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;
    //    private LayoutInflater inflater;
    private List<T> datas;
    private int layoutId;
    public OnItemClickListner onItemClickListner;//单击事件
    public OnItemLongClickListner onItemLongClickListner;//长按单击事件
    private boolean clickFlag = true;//单击事件和长单击事件的屏蔽标识

    public BaseRecyclerViewAdapter(Context context, List<T> datas, int layoutId) {
        this.context = context;
        this.datas = datas;
        this.layoutId = layoutId;
//        this.inflater = LayoutInflater.from(context);
    }

    /**
     * 当解析布局这样子写时：
     * View view = LayoutInflater.from(mContext).inflate(R.layout.activity_main1, parent, false);
     * item中最外层布局的layout属性才起作用；eg:
     * <LinearLayout
     * android:layout_width="match_parent"
     * android:layout_height="match_parent"/>
     * 当解析布局这样子写时：
     * View view = LayoutInflater.from(mContext).inflate(R.layout.activity_main1, null);
     * item中最外层布局的layout属性则不起作用；
     * <p>
     * viewType:多布局使用的；
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //读取xml对内存
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //建立绑定关系
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutId, parent, false);

        BaseViewHolder holder = new BaseViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        bindData(holder, datas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    protected abstract void bindData(BaseViewHolder holder, T data, int position);

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public void setOnItemLongClickListner(OnItemLongClickListner onItemLongClickListner) {
        this.onItemLongClickListner = onItemLongClickListner;
    }

    public interface OnItemClickListner {
        void onItemClickListner(View v, int position);
    }

    public interface OnItemLongClickListner {
        void onItemLongClickListner(View v, int position);
    }
}
