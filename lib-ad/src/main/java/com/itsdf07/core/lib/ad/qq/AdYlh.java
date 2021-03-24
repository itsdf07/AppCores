package com.itsdf07.core.lib.ad.qq;

import android.app.Activity;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.itsdf07.core.lib.ad.AdAdvance;
import com.itsdf07.core.lib.ad.BaseAdPlatform;
import com.itsdf07.core.lib.alog.ALog;
import com.qq.e.ads.interstitial2.UnifiedInterstitialAD;
import com.qq.e.ads.interstitial2.UnifiedInterstitialADListener;
import com.qq.e.comm.util.AdError;

import java.util.Iterator;
import java.util.Locale;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/3/23
 */
public class AdYlh extends BaseAdPlatform implements AdAdvance {
    private static final String TAG = "AdYlh";

    public AdYlh() {
    }

    private static class AdYlhHolder {
        private static final AdYlh INSTANCE = new AdYlh();
    }

    public static final AdYlh getInstance() {
        return AdYlhHolder.INSTANCE;
    }

    @Override
    public String sdkVersion() {
        return "3.4.5.4";
    }

    @Override
    public String adPlatformName() {
        return "穿山甲";
    }

    @Override
    public View loadSplashAdNativeExpress(Activity activity, ViewGroup viewGroup, String adId) {
        return null;
    }

    @Override
    public View loadFlowAdNativeExpress(Activity activity, ViewGroup viewGroup, String adId) {
        return null;
    }

    private UnifiedInterstitialAD iad;

    @Override
    public void loadInterstitialNativeExpress(Activity activity, String adId) {

        ALog.i("YLH 模板渲染-插屏 Ad开始拉取,adId:%s", adId);
        if (this.iad != null) {
            iad.close();
            iad.destroy();
        }
        iad = new UnifiedInterstitialAD(activity, adId, new UnifiedInterstitialADListener() {
            /**
             * 插屏半屏广告加载完毕，此回调后才可以调用 show 方法
             */
            @Override
            public void onADReceive() {
                ALog.i("eCPMLevel:%s", iad.getECPMLevel());
                if (iad != null && iad.isValid()) {
                    iad.showAsPopupWindow();
                } else {
                    Toast.makeText(activity, "请加载广告后再进行展示 ！ ", Toast.LENGTH_LONG).show();
                }
            }

            /**
             * 插屏半屏视频广告，视频素材下载完成
             */
            @Override
            public void onVideoCached() {
                ALog.i("onVideoCached->视频素材加载完成,可开始展示视频广告了");
            }

            /**
             * 广告加载失败，error 对象包含了错误码和错误信息
             *
             * @param adError
             */
            @Override
            public void onNoAD(AdError adError) {
                String msgInfo = String.format(Locale.getDefault(), "onNoAD, error code: %d, error msg: %s",
                        adError.getErrorCode(), adError.getErrorMsg());
                Toast.makeText(activity, msgInfo, Toast.LENGTH_SHORT).show();
                ALog.i("YLH 模板渲染-插屏 AD拉取失败,adId:%s,code:%s,errMsg:%s", adId, adError.getErrorCode(), adError.getErrorMsg());

            }

            /**
             * 插屏半屏广告展开时回调
             */
            @Override
            public void onADOpened() {
                ALog.i("onADOpened...");
            }

            /**
             * 插屏半屏广告曝光时回调
             */
            @Override
            public void onADExposure() {
                ALog.i("onADExposure: 当前 %s 秒广告曝光");
            }

            /**
             * 插屏半屏广告点击时回调
             */
            @Override
            public void onADClicked() {
                ALog.i("onADClicked:clickUrl=%s", (iad.getExt() != null ? iad.getExt().get("clickUrl") : ""));
            }

            /**
             * 插屏半屏广告点击离开应用时回调
             */
            @Override
            public void onADLeftApplication() {
                ALog.i("onADLeftApplication...");
            }

            /**
             * 插屏半屏广告关闭时回调
             */
            @Override
            public void onADClosed() {
                ALog.i("onADClosed...");
            }
        });
        iad.loadAD();
    }

}
