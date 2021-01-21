package com.itsdf07.core.app.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class RoundedCornerLayout extends LinearLayout {
    private final static float CORNER_RADIUS = 15.0f;
    public float cornerRadius;
    private Path path;

    public RoundedCornerLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public RoundedCornerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public RoundedCornerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        cornerRadius = CORNER_RADIUS * getDensity();
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    public void updateCornerRadius(float radius) {
        float r = radius * getDensity();

        cornerRadius = r;
        this.path = new Path();
        this.path.addRoundRect(new RectF(0, 0, getWidth(), getHeight()), cornerRadius, cornerRadius, Path.Direction.CW);
    }

    public void updateTopCornerRadius(float radius) {
        float r = radius * getDensity();

        cornerRadius = r;
        this.path = new Path();
        this.path.addRoundRect(new RectF(0, 0, getWidth(), getHeight()), cornerRadius, cornerRadius, Path.Direction.CW);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        this.path = new Path();
        this.path.addRoundRect(new RectF(0, 0, width, height), cornerRadius, cornerRadius, Path.Direction.CW);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (this.path != null) {
            canvas.clipPath(this.path);
        }
        super.dispatchDraw(canvas);
    }

    private float getDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }


}
