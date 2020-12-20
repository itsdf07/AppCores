package com.itsdf07.core.app

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.itsdf07.core.lib.alog.ALog
import com.itsdf07.core.lib.alog.ALogLevel
import com.itsdf07.core.lib.net.NetInit
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure
import com.umeng.commonsdk.statistics.common.DeviceConfig


/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/11/29
 */
class MyApplication : Application(), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        //App in background
        ALog.i("APP 退入到后台")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        // App in foreground
        ALog.i("APP 唤醒到前台")
    }

    override fun onCreate() {
        super.onCreate()
        ALog.init().apply {
            logLevel = ALogLevel.FULL
            tag = "tag-itsdf07"
            isShowThreadInfo = false
        }
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        initNet()
        initUmeng()
    }

    private fun initNet() {
        if (GlobalConfigs.is98Debug) {
            NetInit.init(this)
                .showLog(true)
                .withApiHost(GlobalConfigs.BASE_URL_98)
                .configure()
        } else {
            NetInit.init(this)
                .withApiHost(GlobalConfigs.BASE_URL_LOCAL)
                .configure()
        }
    }

    private fun initUmeng() {
        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(true)
        /**
         * 注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，也需要在App代码中调
         * 用初始化接口（如需要使用AndroidManifest.xml中配置好的appkey和channel值，
         * UMConfigure.init调用中appkey和channel参数请置为null）。
         */
        UMConfigure.init(
            this,
            "5fdaf63ddd28915339221f17",
            "Umeng_test",
            UMConfigure.DEVICE_TYPE_PHONE,
            null
        )
        //设置页面采集模式
        //设置页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)
    }

    fun getTestDeviceInfo(): Array<String?>? {
        val deviceInfo = arrayOfNulls<String>(2)
        try {
            deviceInfo[0] = DeviceConfig.getDeviceIdForGeneral(this)
            deviceInfo[1] = DeviceConfig.getMac(this)
        } catch (e: Exception) {
        }
        return deviceInfo
    }

}