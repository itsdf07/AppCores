package com.itsdf07.core.app.ui.activity.showimg;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.itsdf07.core.app.R;
import com.itsdf07.core.app.common.adapter.BaseViewHolder;
import com.itsdf07.core.app.view.ZoomImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/2/5
 */
public class BigImgAdapter extends RecyclerView.Adapter<BigImgAdapter.NormalHolder> {
    private List<String> mDatas;
    private Context mContext;

    public BigImgAdapter(Context context, List<String> imgs) {
        if (imgs == null) {
            imgs = new ArrayList<>();
        }
        mDatas = imgs;
        mContext = context;
    }

    @NonNull
    @Override
    public BigImgAdapter.NormalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show_big_img, parent, false);
        return new NormalHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NormalHolder holder, int position) {
//        holder.imageView.setImageResource(R.mipmap.h_pic_4);
        Glide.with(mContext).load(mDatas.get(position)).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                return false;
            }
        }).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class NormalHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public NormalHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.original_img);
        }
    }

}
