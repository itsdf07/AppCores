package com.itsdf07.core.lib.ad;

import android.app.Application;
import android.content.Context;

import com.itsdf07.core.lib.ad.csj.TTAdManagerHolder;
import com.itsdf07.core.lib.alog.ALog;
import com.itsdf07.core.lib.alog.ALogLevel;
import com.qq.e.comm.managers.GDTADManager;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/3/23
 */
public class AdApplication extends Application {
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        ALog.init().setTag("AD_LOG").setLogLevel(ALogLevel.FULL);
        initADGDT();
        initADCSJ();
    }


    private void initADGDT() {
        GDTADManager.getInstance().initWith(this, "1111567150");
    }

    private void initADCSJ() {
//        ALog.i("initADCSJ ...");
        //穿山甲SDK初始化
        //强烈建议在应用对应的Application#onCreate()方法中调用，避免出现content为null的异常
        TTAdManagerHolder.init(this);
        //如果明确某个进程不会使用到广告SDK，可以只针对特定进程初始化广告SDK的content
        //if (PROCESS_NAME_XXXX.equals(processName)) {
        //   TTAdManagerHolder.init(this)
        //}
    }
}
