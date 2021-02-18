package com.itsdf07.core.app.common;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/2/10
 */


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;


/**
 * 万能分割
 * 使用此类  item的tag会被占用  如果外部使用会造成乱
 * 外部可以使用 item.setTag(key,obj)
 */

public abstract class UniversalItemDecoration extends RecyclerView.ItemDecoration {

    private Map<Integer, Decoration> decorations = new HashMap<>();

    /**
     * 在子视图上设置绘制范围，并绘制内容
     * 绘制图层在ItemView以下，所以如果绘制区域与ItemView区域相重叠，会被遮挡
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        // 获取RecyclerView的Child view的个数
        final int childSize = parent.getChildCount();
        // 遍历每个Item，分别获取它们的位置信息，然后再绘制对应的分割线
        for (int i = 0; i < childSize; i++) {
            // 获取每个Item的位置
            final View child = parent.getChildAt(i);
            //获取在getItemOffsets存起来的position
            int position = string2Int(child.getTag().toString(), 0);
            Decoration decoration = decorations.get(position);

            if (decoration == null) {
                continue;
            }
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();

            //view的上下左右包括 Margin
            int bottom = child.getBottom() + layoutParams.bottomMargin;
            int left = child.getLeft() - layoutParams.leftMargin;
            int right = child.getRight() + layoutParams.rightMargin;
            int top = child.getTop() - layoutParams.topMargin;

            //下面的
            decoration.drawItemOffsets(c, left - decoration.left, bottom, right + decoration.right, bottom + decoration.bottom);
            //上面的
            decoration.drawItemOffsets(c, left - decoration.left, top - decoration.top, right + decoration.right, top);
            //左边的
            decoration.drawItemOffsets(c, left - decoration.left, top, left, bottom);
            //右边的
            decoration.drawItemOffsets(c, right, top, right + decoration.right, bottom);
        }

    }

    /**
     * 同样是绘制内容，但与onDraw（）的区别是：绘制在图层的最上层
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    /**
     * 设置ItemView的内嵌偏移长度（inset）
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        //获取position
        int position = parent.getChildAdapterPosition(view);
        view.setTag(position);

        //获取调用者返回的Decoration
        Decoration decoration = getItemOffsets(position);

        if (decoration != null) {
            //偏移量设置给item
            outRect.set(decoration.left, decoration.top, decoration.right, decoration.bottom);
            //存起来在onDraw用
            decorations.put(position, decoration);
        } else {
            //不要线
            decoration = null;
        }


    }


    /***
     * 需调用者返回分割线对象  上下左右 和颜色值
     * @param position
     * @return
     */
    public abstract Decoration getItemOffsets(int position);

    /**
     * 分割线
     */
    public abstract static class Decoration {

        public int left, right, top, bottom;

        /**
         * 根据偏移量设定的 当前的线在界面中的坐标
         *
         * @param leftZ
         * @param topZ
         * @param rightZ
         * @param bottomZ
         */
        public abstract void drawItemOffsets(Canvas c, int leftZ, int topZ, int rightZ, int bottomZ);

    }

    public static class ColorDecoration extends Decoration {

        private Paint mPaint;
        public int decorationColor = Color.TRANSPARENT;

        public ColorDecoration() {
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setStyle(Paint.Style.FILL);
        }

        @Override
        public void drawItemOffsets(Canvas c, int leftZ, int topZ, int rightZ, int bottomZ) {

            mPaint.setColor(decorationColor);
            c.drawRect(leftZ, topZ, rightZ, bottomZ, mPaint);
        }

    }


    public static int string2Int(String s, int defValue) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return defValue;
        }
    }
}