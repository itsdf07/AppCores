package com.itsdf07.core.app.common.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/**
 * @Description: viewpage的适配器
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/23
 */
public class BasePagerAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    //判断是否是否为同一张图片，这里返回方法中的两个参数做比较就可以,相同则返回true

    /**
     * 判断是否由对象生成界面
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

    /**
     *
     * @param container
     * @param position
     * @return 这个对象表明了PagerAdapter适配器选择哪个对象*放在当前的ViewPager中
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
