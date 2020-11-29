package com.itsdf07.core.app

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.itsdf07.core.lib.alog.ALog
import com.itsdf07.core.lib.alog.ALogLevel

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/11/29
 */
class MyApplication : Application(), LifecycleObserver {
    override fun onCreate() {
        super.onCreate()
        ALog.init().apply {
            logLevel = ALogLevel.FULL
            tag = "tag-itsdf07"
        }
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

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
}