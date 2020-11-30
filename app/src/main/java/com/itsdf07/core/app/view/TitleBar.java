package com.itsdf07.core.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;

import com.itsdf07.core.app.R;

/**
 * @Description: 自定义一个通用的TitleBar
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/11/29
 */
public class TitleBar extends RelativeLayout {
    /**
     * title默认色值
     */
    private static int TITLECOLOR = R.color.color_black;
    /**
     * 返回按钮
     */
    private ImageButton mBackButton;
    /**
     * 分享按钮
     */
    private ImageButton mShareButton;
    private TextView mTitle;
    private int mTitleColorResId = TITLECOLOR;
    private float mTitleTextSize;
    /**
     * 是否加粗
     */
    private boolean mTitleBold;
    private OnBackClickListener mOnBackClickListener;
    private OnShareClickListener mOnShareClickListener;
    private View mBottomLine;
    /**
     * 是否开启分享按钮
     */
    private boolean mEnableShare;
    private int mTitleTextResId;
    private int mBackIconResId;
    private int mShareIconResId;
    private boolean mShowBottomLine;


    public interface OnBackClickListener {
        void onBackClick();
    }

    public interface OnShareClickListener {
        void onShareClick();
    }

    public TitleBar(Context context) {
        super(context);
        initViews(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        handleAttributes(context, attrs);
        initViews(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        handleAttributes(context, attrs);
        initViews(context);
    }

    private void handleAttributes(Context context, AttributeSet attrs) {
        try {
            TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
            mEnableShare = styledAttrs.getBoolean(R.styleable.TitleBar_enable_share, false);
            mTitleColorResId = styledAttrs.getResourceId(R.styleable.TitleBar_title_color, TITLECOLOR);
            mTitleBold = styledAttrs.getBoolean(R.styleable.TitleBar_title_text_bold, false);
            mTitleTextResId = styledAttrs.getResourceId(R.styleable.TitleBar_title_text, 0);
            mTitleTextSize = styledAttrs.getDimension(R.styleable.TitleBar_title_text_size, 18);

            mBackIconResId = styledAttrs.getResourceId(R.styleable.TitleBar_back_icon, R.mipmap.ic_back_nav);
            mShareIconResId = styledAttrs.getResourceId(R.styleable.TitleBar_share_icon, R.mipmap.ic_share);
            mShowBottomLine = styledAttrs.getBoolean(R.styleable.TitleBar_show_bottom_line, false);
            styledAttrs.recycle();
        } catch (Exception e) {

        }

    }

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_title_bar, this);
        mBackButton = findViewById(R.id.title_back);
        mBackButton.setImageResource(mBackIconResId);
        mShareButton = findViewById(R.id.title_share);
        if (mEnableShare) {
            mShareButton.setVisibility(VISIBLE);
            mShareButton.setImageResource(mShareIconResId);
        } else {
            mShareButton.setVisibility(GONE);
        }
        mTitle = findViewById(R.id.title_tv);
        if (mTitleTextResId == 0) {
            mTitle.setVisibility(GONE);
        } else {
            mTitle.setVisibility(VISIBLE);
            mTitle.setText(mTitleTextResId);
            mTitle.setTextColor(context.getResources().getColor(mTitleColorResId));
        }
        mBottomLine = findViewById(R.id.bottom_line);
        if (!mShowBottomLine) {
            mBottomLine.setVisibility(GONE);
        }
        mBackButton.setOnClickListener(mOnClickListener);
        mTitle.setOnClickListener(mOnClickListener);
        mShareButton.setOnClickListener(mOnClickListener);
    }

    public void setTitleColor(int titleColor) {
        mTitle.setTextColor(titleColor);
    }

    public void setBackIconResId(int backIconResId) {
        mBackIconResId = backIconResId;
        mBackButton.setImageResource(backIconResId);
    }


    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setTitleVisibility(int visibility) {
        mTitle.setVisibility(visibility);
    }

    public void setShareIconResId(int shareIconResId) {
        mShareIconResId = shareIconResId;
        mShareButton.setImageResource(shareIconResId);
    }

    public void setOnBackClickListener(OnBackClickListener listener) {
        mOnBackClickListener = listener;
    }

    public void setOnShareClickListener(OnShareClickListener listener) {
        mOnShareClickListener = listener;
    }

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.title_back:
                    if (mOnBackClickListener != null) {
                        mOnBackClickListener.onBackClick();
                    }
                    break;

                case R.id.title_tv:
                    break;
                case R.id.title_share:
                    if (mOnShareClickListener != null) {
                        mOnShareClickListener.onShareClick();
                    }
                    break;
                default:
                    break;

            }
        }
    };


}