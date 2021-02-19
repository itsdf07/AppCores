package com.itsdf07.core.app.ui.fragment.home;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.itsdf07.core.app.jk.JKAppCacheCommon;
import com.itsdf07.core.app.ui.fragment.home.bean.TurnsBean;

import java.util.List;


/**
 * @Description: 首页-发现-吸顶部分轮播区（turns）对应适配器
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/21
 */
public class DiscoverTurnsAdapter extends PagerAdapter {
    private List<TurnsBean> slidesData;
    private Context mContext;

    public DiscoverTurnsAdapter(DiscoverFragment fragment) {
        mContext = fragment.requireContext();
    }

    @Override
    public int getCount() {
        if (slidesData != null) {
            return slidesData.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int realPosition = position % slidesData.size();
        ImageView imageView = new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(container.getContext())
                .load(slidesData.get(realPosition).getImg())
                .into(imageView);
        container.addView(imageView);
        imageView.setOnClickListener(v -> {
            if (slidesData.get(position).isLogin() == 1 && TextUtils.isEmpty(JKAppCacheCommon.APP_USER_AUTH)) {
                Toast.makeText(mContext, "当前需求登录，并且当前用户未登录", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, "跳转", Toast.LENGTH_SHORT).show();
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    public void setData(List<TurnsBean> list) {
        slidesData = list;
        notifyDataSetChanged();
    }

    public int getDataRealSize() {
        if (slidesData != null) {
            return slidesData.size();
        }
        return 0;
    }
}
