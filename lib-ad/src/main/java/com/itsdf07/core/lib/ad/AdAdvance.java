package com.itsdf07.core.lib.ad;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Description: 广告发展
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/3/22
 */
public interface AdAdvance {
    /**
     * 当前 AD 平台对应的 SDK 版本信息
     *
     * @return
     */
    String sdkVersion();

    /**
     * 当前的 AD 平台名称
     *
     * @return
     */
    String adPlatformName();

    /**
     * 模板渲染-开屏 AD
     *
     * @param activity
     * @param viewGroup
     * @param adId
     * @return
     */
    View loadSplashAdNativeExpress(Activity activity, ViewGroup viewGroup, String adId);

    /**
     * 模板渲染-信息流
     *
     * @param activity
     * @param viewGroup
     * @param adId
     * @return
     */
    View loadFlowAdNativeExpress(Activity activity, ViewGroup viewGroup, String adId);

    /**
     * 模板渲染-插屏 AD
     *
     * @param adId
     */
    void loadInterstitialNativeExpress(Activity activity, String adId);

}
