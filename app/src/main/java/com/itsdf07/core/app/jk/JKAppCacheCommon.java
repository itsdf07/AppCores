package com.itsdf07.core.app.jk;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/8
 */
public class JKAppCacheCommon {
    /**
     * 首页签到背景图
     */
    public static String SIGN_URL_BG_IMG_HOMG = "";
    /**
     * 首页签到跳转目标URL地址
     */
    public static String SIGN_URL_TARGET_URL_HOMG = "";

    /**
     * 消息背景图
     */
    public static String MESSAGE_COMMONITY_URL_BG_IMG = "";

    /**
     * 用户授权头，客户端是否登录的依据，并成在所有与服务端交互时出现登录过期时，都需要清除与这个有关的信息，SP对应的key
     *
     */
    public static String APP_USER_AUTH = "";
    /**
     * 设备唯一标识
     *
     */
    public static String APP_DEVICEID = "";

    /**
     * 用户ID
     */
    public static int APP_USER_ID = 0;

    /**
     * 是否显示抖音指向 config接口对于的isDouYin
     */
    public static int APP_IS_SHOW_DOUYI = 0;

    /**
     * 已登录用户的头像目标地址
     */
    public static String APP_USER_AVATAR_URL = "";

    /**
     * 已登录的用户作品数量
     */
    public static int APP_USER_WARK_COUNT = 0;
    /**
     * 已登录的用户色卡数量
     */
    public static int APP_USER_COLORCARD_COUNT = 0;

    public static String APP_USER_NICKNAME = "JK 设计师";

    public static String QQ_OPENID = "";
    public static String QQ_ACCESS_TOKEN = "";
    public static String QQ_NICKNAME = "";
    public static String QQ_AVATAR_URL = "";
    public static String QQ_UNIONID = "";

    public static String WECHAT_CODE = "";
}
