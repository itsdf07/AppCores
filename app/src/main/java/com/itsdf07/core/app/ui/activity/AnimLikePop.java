package com.itsdf07.core.app.ui.activity;

import android.animation.Animator;
import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.airbnb.lottie.LottieAnimationView;
import com.itsdf07.core.app.R;


/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/2/8
 */
public class AnimLikePop extends PopupWindow {

    public AnimLikePop(Activity context) {
//        // 实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0x66000000);
//        // 设置弹出窗体的背景
//        this.setBackgroundDrawable(dw);
//        // 设置弹出窗体的宽和高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        setFocusable(true);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_like_anim, null);
        LottieAnimationView likeView = view.findViewById(R.id.like_anim);
        likeView.getLayoutParams().width = 720 * 5 / 10;
        likeView.getLayoutParams().height = 720 * 5 / 10;
        this.setContentView(view);
        likeView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                dismiss();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        showAtLocation(context.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

}
