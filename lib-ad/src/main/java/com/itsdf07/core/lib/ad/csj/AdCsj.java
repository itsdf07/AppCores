package com.itsdf07.core.lib.ad.csj;

import android.app.Activity;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.MainThread;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAppDownloadListener;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import com.bytedance.sdk.openadsdk.TTSplashAd;
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
        int screenWidth = activity.getWindowManager().getDefaultDisplay().getWidth();
        int screenHeight = activity.getWindowManager().getDefaultDisplay().getHeight();
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(adId)
                .setImageAcceptedSize(screenWidth, screenHeight)
                .build();
        TTAdNative mTTAdNative = TTAdManagerHolder.get().createAdNative(activity);
        //step4:请求广告，调用开屏广告异步请求接口，对请求回调的广告作渲染处理
        mTTAdNative.loadSplashAd(adSlot, new TTAdNative.SplashAdListener() {
            @Override
            @MainThread
            public void onError(int code, String message) {
                Log.d(TAG, String.valueOf(message));
            }

            @Override
            @MainThread
            public void onTimeout() {
            }

            @Override
            @MainThread
            public void onSplashAdLoad(TTSplashAd ad) {
                Log.d(TAG, "开屏广告请求成功");
                if (ad == null) {
                    return;
                }
                //获取SplashView
                View view = ad.getSplashView();
                if (view != null && viewGroup != null) {
                    viewGroup.removeAllViews();
                    //把SplashView 添加到ViewGroup中,注意开屏广告view：width >=70%屏幕宽；height >=50%屏幕高
                    viewGroup.addView(view);
                    //设置不开启开屏广告倒计时功能以及不显示跳过按钮,如果这么设置，您需要自定义倒计时逻辑
                    //ad.setNotAllowSdkCountdown();
                }

                //设置SplashView的交互监听器
                ad.setSplashInteractionListener(new TTSplashAd.AdInteractionListener() {
                    @Override
                    public void onAdClicked(View view, int type) {
                        Log.d(TAG, "onAdClicked");
                    }

                    @Override
                    public void onAdShow(View view, int type) {
                        Log.d(TAG, "onAdShow");
                    }

                    @Override
                    public void onAdSkip() {
                        Log.d(TAG, "onAdSkip");

                    }

                    @Override
                    public void onAdTimeOver() {
                        Log.d(TAG, "onAdTimeOver");
                        activity.finish();
                    }
                });
                if (ad.getInteractionType() == TTAdConstant.INTERACTION_TYPE_DOWNLOAD) {
                    ad.setDownloadListener(new TTAppDownloadListener() {
                        boolean hasShow = false;

                        @Override
                        public void onIdle() {
                        }

                        @Override
                        public void onDownloadActive(long totalBytes, long currBytes, String fileName, String appName) {
                            if (!hasShow) {
                                hasShow = true;
                            }
                        }

                        @Override
                        public void onDownloadPaused(long totalBytes, long currBytes, String fileName, String appName) {

                        }

                        @Override
                        public void onDownloadFailed(long totalBytes, long currBytes, String fileName, String appName) {

                        }

                        @Override
                        public void onDownloadFinished(long totalBytes, String fileName, String appName) {

                        }

                        @Override
                        public void onInstalled(String fileName, String appName) {

                        }
                    });
                }
            }
        }, 3000);
        return null;
    }

    @Override
    public View loadFlowAdNativeExpress(Activity activity, ViewGroup viewGroup, String adId) {
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(adId) //广告位id
                .setAdCount(1) //请求广告数量为1到3条,建议设置为1
                .setExpressViewAcceptedSize(px2dp(activity, width), 0) //期望模板广告view的size,单位dp
                .build();

        TTAdNative mTTAdNative = TTAdManagerHolder.get().createAdNative(activity);
        //step5:请求广告，对请求回调的广告作渲染处理
        mTTAdNative.loadNativeExpressAd(adSlot, new TTAdNative.NativeExpressAdListener() {
            /**
             *  广告请求失败
             * @param code
             * @param message
             */
            @Override
            public void onError(int code, String message) {
                ALog.i("CSJ 模板渲染-信息流 AD拉取失败,adId:%s,code:%s,errMsg:%s", adId, code, message);
                viewGroup.removeAllViews();
            }

            /**
             * 广告请求成功的回调，客户端可在该回调中调用render()进行广告渲染
             * @param ads
             */
            @Override
            public void onNativeExpressAdLoad(List<TTNativeExpressAd> ads) {
                if (ads == null || ads.size() == 0) {
                    ALog.i("CSJ 模板渲染-信息流 AD拉取成功,但是没有数据返回");
                    onError(-1, "CSJ 模板渲染-信息流 AD拉取成功,但是没有数据返回");
                    return;
                }
                TTNativeExpressAd mTTAd = ads.get(0);
                long startTime = System.currentTimeMillis();

                //广告点击事件监听器
                mTTAd.setExpressInteractionListener(new TTNativeExpressAd.ExpressAdInteractionListener() {
                    /**
                     * 广告点击的回调，点击后的动作由sdk控制 点击量 可在穿山甲提供的方法里进行埋点统计
                     * @param view
                     * @param type
                     */
                    @Override
                    public void onAdClicked(View view, int type) {
                        ALog.i("CSJ平台的模板信息流AD被点击了,type:%s", type);
                    }

                    /**
                     * 广告展示回调，展示量 可在穿山甲提供的方法里进行埋点统计
                     * @param view
                     * @param type
                     */
                    @Override
                    public void onAdShow(View view, int type) {
                        ALog.i("CSJ平台的模板信息流AD被展示了,type:%s", type);
                    }

                    /**
                     * 个性化模板渲染失败
                     * @param view
                     * @param errMsg
                     * @param code
                     */
                    @Override
                    public void onRenderFail(View view, String errMsg, int code) {
                        ALog.i("CSJ平台的模板信息流AD渲染失败了,code:%s,过程耗时:%s,errMsg:%s", code, (System.currentTimeMillis() - startTime), errMsg);
                        onError(-1, "CSJ 模板渲染-插屏 渲染失败");
                    }

                    /**
                     * 个性化模板渲染成功，客户端可在该回调中把广告的view直接add要显示的控件
                     * @param view
                     * @param width
                     * @param height
                     */
                    @Override
                    public void onRenderSuccess(View view, float width, float height) {
                        ALog.i("CSJ平台的模板信息流AD渲染成功了,width:%s,height:%s,过程耗时:%s", width, height, (System.currentTimeMillis() - startTime));
                        //返回view的宽高 单位 dp
                        viewGroup.removeAllViews();
                        viewGroup.addView(view);
                    }
                });
                //dislike设置
//                bindDislikeCSJ(mTTAd, viewGroup, false);
                if (mTTAd.getInteractionType() != TTAdConstant.INTERACTION_TYPE_DOWNLOAD) {
//                    return;
                }
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
        return null;
    }

    @Override
    public void loadInterstitialNativeExpress(Activity activity, String adId) {
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(adId) //广告位id
                .setAdCount(1) //请求广告数量为1到3条,建议设置为1
                .setExpressViewAcceptedSize(px2dp(activity, width), px2dp(activity, width)) //期望模板广告view的size,单位dp
                .build();
        ALog.iTag(TAG, "模板渲染-插屏 Ad开始拉取,adId:%s,expressViewWidth：%s,expressViewHeight:%s", adId, px2dp(activity, 128), px2dp(activity, 128));
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
