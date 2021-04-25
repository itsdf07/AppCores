package com.itsdf07.core.lib.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import androidx.annotation.RequiresPermission;

import static android.Manifest.permission.READ_PHONE_STATE;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/4/14
 */
public class DeviceUtils {

    /**
     * @return AndroidId
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidID(Context context) {
        String id = Settings.Secure.getString(
                context.getContentResolver(),
                Settings.Secure.ANDROID_ID
        );
        if ("9774d56d682e549c".equals(id)) return "";
        return id == null ? "" : id;
    }


    /**
     * 获取imei号 <br>
     * IMEI:国际移动设备识别码(International Mobile Equipment Identity)，是区别移动设备的标志，
     * 是由15位数字组成的"电子串号"，它与每台手机一一对应，而且该码是全世界唯一的。每一只手机在组装完
     * 成后都将被赋予一个全球唯一的一组号码，这个号码从生产到交付使用都将被制造生产的厂商所记录。
     *
     * @param context
     * @return
     */
    public static String getImei(Context context) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephonyMgr == null || mTelephonyMgr.getDeviceId() == null) {
            return "";
        }
        return mTelephonyMgr.getDeviceId();
//        return "863402010319246";
    }

    /**
     * 获取imsi号 <br>
     * IMSI:国际移动用户识别码(International Mobile SubscriberIdentification
     * Number)，是区别移动用户的标志，
     * 储存在SIM卡中，可用于区别移动用户的有效信息。其总长度不超过15位，同样使用0～9的数字。其中MCC是移动用户所
     * 属国家代号，占3位数字，中国的MCC规定为460；MNC是移动网号码，最多由两位数字组成，用于识别移动用户所归属的
     * 移动通信网；MSIN是移动用户识别码，用以识别某一移动通信网中的移动用户。
     *
     * @param context
     * @return
     */
    public static String getImsi(Context context) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyMgr.getSubscriberId();
    }

    /**
     * @return 厂商，即品牌
     */
    public static String getBrand() {
        return Build.BRAND;
    }

    /**
     * @return 设备型号
     */
    public static String getModel() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");//正则表达式去掉空白字符（可能是空格、制表符、其他空白）
        } else {
            model = "";
        }
        return model;
    }

    /**
     * Return the serial of device.
     *
     * @return the serial of device
     */
    @SuppressLint("HardwareIds")
    public static String getSerial() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            try {
                return Build.getSerial();
            } catch (SecurityException e) {
                e.printStackTrace();
                return "";
            }
        }
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? Build.getSerial() : Build.SERIAL;
    }

    /**
     * @return 获取设备厂商/制造商
     */
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }
}
