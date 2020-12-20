package com.itsdf07.core.lib.net.log;

import android.util.Log;

import com.itsdf07.core.lib.net.ConfigKeys;
import com.itsdf07.core.lib.net.NetInit;


/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/11/13
 */
public class LogUtils {
    public static void logi(String logMsg) {
        boolean isShowLog = NetInit.getConfiguration(ConfigKeys.NET_LOG.name());
        if (!isShowLog) {
            return;
        }
        Log.i("NetLog", logMsg);
    }
}
