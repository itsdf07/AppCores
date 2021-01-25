package com.itsdf07.core.app.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/21
 */
public class SlideViewPager extends ViewPager {
    private OnViewPagerTouchListen mOnViewPagerTouchListen;

    public SlideViewPager(@NonNull Context context) {
        super(context);
    }

    public SlideViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mOnViewPagerTouchListen != null) {
                    mOnViewPagerTouchListen.onViewPagerTouch(true);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (mOnViewPagerTouchListen != null) {
                    mOnViewPagerTouchListen.onViewPagerTouch(false);
                }
        }
        return super.onTouchEvent(ev);
    }

    public void setOnViewPagerTouchListen(OnViewPagerTouchListen onViewPagerTouchListen) {
        this.mOnViewPagerTouchListen = onViewPagerTouchListen;
    }

    public interface OnViewPagerTouchListen {
        void onViewPagerTouch(boolean isTouch);
    }
}
