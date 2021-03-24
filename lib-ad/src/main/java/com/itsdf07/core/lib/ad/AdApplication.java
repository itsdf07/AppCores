package com.itsdf07.core.lib.ad;

import android.app.Application;
import android.content.Context;

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
//        initADGDT();
    }


    private void initADGDT() {
        GDTADManager.getInstance().initWith(this, "1111479260");
    }
}
