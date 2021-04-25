package com.itsdf07.core.lib.common;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/4/25
 */
public class AppUtils {

    public static boolean isAppInstalled(Context context, final String pkgName) {
        if (TextUtils.isEmpty(pkgName)) {
            return false;
        }
        PackageManager pm = context.getPackageManager();
        try {
            return pm.getApplicationInfo(pkgName, 0).enabled;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }

    }
}
