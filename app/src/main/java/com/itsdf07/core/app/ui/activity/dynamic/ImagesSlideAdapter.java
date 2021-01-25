package com.itsdf07.core.app.ui.activity.dynamic;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.itsdf07.core.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 图片轮播适配器
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/21
 */
public class ImagesSlideAdapter extends PagerAdapter {
    private static final String TAG = "ShufflingAdapter";
    private List<ImagesSlideBean> imagesSlideBeans;

    @Override
    public int getCount() {
        if (imagesSlideBeans != null) {
            return imagesSlideBeans.size();
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
//        int realPosition = position % imagesSlideBeans.size();
        ViewGroup.LayoutParams params = container.getLayoutParams();
        ImageView imageView = new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        RequestOptions requestOptions = RequestOptions.bitmapTransform(new RoundedCorners(20));
        Glide.with(container.getContext())
                .load(imagesSlideBeans.get(position).getImgUrl())
                .apply(requestOptions)
                .placeholder(R.mipmap.main_img_creation)
                .into(imageView);
        container.addView(imageView);
        imageView.setOnClickListener(v -> {
            if (TextUtils.isEmpty(imagesSlideBeans.get(position).getImgUrl())) {
                Toast.makeText(container.getContext(), "跳转失败", Toast.LENGTH_SHORT).show();
            } else {
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    public void setData(List<ImagesSlideBean> list) {
        imagesSlideBeans = new ArrayList<>();
        imagesSlideBeans.addAll(list);
        notifyDataSetChanged();
    }

    public int getDataRealSize() {
        if (imagesSlideBeans != null) {
            return imagesSlideBeans.size();
        }
        return 0;
    }
}
