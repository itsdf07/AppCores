package com.itsdf07.core.lib.ad.csj;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAppDownloadListener;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import com.itsdf07.core.lib.ad.AdAdvance;
import com.itsdf07.core.lib.ad.BaseAdPlatform;
import com.itsdf07.core.lib.alog.ALog;

import java.util.List;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/3/23
 */
public class AdCsj extends BaseAdPlatform implements AdAdvance {
    private static final String TAG = "AdCsj";

    public AdCsj() {
    }

    private static class AdCsjHolder {
        private static final AdCsj INSTANCE = new AdCsj();
    }

    public static final AdCsj getInstance() {
        return AdCsjHolder.INSTANCE;
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
    public void loadInterstitialNativeExpress(Activity activity, String adId) {
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(adId) //广告位id
                .setAdCount(1) //请求广告数量为1到3条,建议设置为1
                .setExpressViewAcceptedSize(px2dp(activity, 128), px2dp(activity, 128)) //期望模板广告view的size,单位dp
                .build();
        ALog.iTag(TAG,"模板渲染-插屏 Ad开始拉取,adId:%s,expressViewWidth：%s,expressViewHeight:%s", adId, px2dp(activity, 128), px2dp(activity, 128));
        TTAdNative mTTAdNative = TTAdManagerHolder.get().createAdNative(activity);
        mTTAdNative.loadInteractionExpressAd(adSlot, new TTAdNative.NativeExpressAdListener() {
            @Override
            public void onError(int code, String message) {
                ALog.i("CSJ 模板渲染-插屏 AD拉取失败,adId:%s,code:%s,errMsg:%s", adId, code, message);
            }

            @Override
            public void onNativeExpressAdLoad(List<TTNativeExpressAd> ads) {
                if (ads == null || ads.size() == 0) {
                    onError(-1, "CSJ 模板渲染-插屏 AD拉取成功,但是没有数据返回");
                    ALog.i("CSJ 模板渲染-插屏 AD拉取成功,但是没有数据返回");
                    return;
                }
                long startTime = System.currentTimeMillis();
                TTNativeExpressAd mTTAd = ads.get(0);
                mTTAd.setExpressInteractionListener(new TTNativeExpressAd.AdInteractionListener() {
                    @Override
                    public void onAdDismiss() {
                        ALog.i("CSJ 模板渲染-插屏 AD窗口关闭了");
                        mTTAd.destroy();
                    }

                    @Override
                    public void onAdClicked(View view, int type) {
                        ALog.i("CSJ 模板渲染-插屏 AD被点击了,type:%s", type);
                    }

                    @Override
                    public void onAdShow(View view, int type) {
                        ALog.i("CSJ 模板渲染-插屏 AD被展示了,type:%s", type);
                    }

                    @Override
                    public void onRenderFail(View view, String errMsg, int code) {
                        ALog.i("CSJ 模板渲染-插屏 AD渲染失败了,code:%s,过程耗时:%s,errMsg:%s", code, (System.currentTimeMillis() - startTime), errMsg);
                        onError(-1, "CSJ 模板渲染-插屏 渲染失败");
                    }

                    @Override
                    public void onRenderSuccess(View view, float width, float height) {
                        Log.e("ExpressView", "render suc:");
                        //返回view的宽高 单位 dp
                        mTTAd.showInteractionExpressAd(activity);


                    }
                });


                mTTAd.setDownloadListener(new TTAppDownloadListener() {
                    @Override
                    public void onIdle() {
                        ALog.i("onIdle....");
                    }

                    @Override
                    public void onDownloadActive(long totalBytes, long currBytes, String fileName, String appName) {
                        ALog.i("totalBytes：%s,currBytes:%s,appName:%s,fileName:%s", totalBytes, currBytes, appName, fileName);
                    }

                    @Override
                    public void onDownloadPaused(long totalBytes, long currBytes, String fileName, String appName) {
                        ALog.i("totalBytes：%s,currBytes:%s,appName:%s,fileName:%s", totalBytes, currBytes, appName, fileName);
                    }

                    @Override
                    public void onDownloadFailed(long totalBytes, long currBytes, String fileName, String appName) {
                        ALog.i("totalBytes：%s,currBytes:%s,appName:%s,fileName:%s", totalBytes, currBytes, appName, fileName);
                    }

                    @Override
                    public void onInstalled(String fileName, String appName) {
                        ALog.i("appName:%s,fileName:%s", appName, fileName);
                    }

                    @Override
                    public void onDownloadFinished(long totalBytes, String fileName, String appName) {
                        ALog.i("totalBytes：%s,appName:%s,fileName:%s", totalBytes, appName, fileName);
                    }
                });
                mTTAd.render();


            }
        });
    }

}
