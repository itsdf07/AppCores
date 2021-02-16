package com.itsdf07.core.app.jk;

import java.util.HashMap;
import java.util.Map;

public class JKUrl {
    //    测试环境地址：https://www.imjk.club/testapi/
    private static final String BASE_URL_TEST = "https://www.imjk.club/testapi/";
    //    正式环境地址：https://www.imjk.club/api2/
    private static final String BASE_URL_API = "https://www.imjk.club/api2/";
    public static String BASE_URL = BASE_URL_TEST;
    public static final String GET_CONFIG = BASE_URL + "config";
    public static final String GET_CHECKPHONE = BASE_URL + "check_phone";
    public static final String GET_VERCODE = BASE_URL + "vercode";
    public static final String GET_USER_VERCODE = BASE_URL + "user_vercode";
    public static final String GET_CHECK_VERCODE = BASE_URL + "check_vercode";
    public static final String GET_REGISTER_PHONE = BASE_URL + "register_phone";
    public static final String GET_LOGIN_PHONE = BASE_URL + "login_phone";
    public static final String GET_REST_PASSWORD = BASE_URL + "reset_password";
    public static final String GET_USER_PHONE = BASE_URL + "user_phone";
    public static final String GET_UMENG_DEVICE = BASE_URL + "umeng_device";
    public static final String GET_UPDATE_PASSWORD = BASE_URL + "password";

    public static final String GET_IMAGE_LIST = BASE_URL + "work_v2_list";
    public static final String GET_CARD_LIST = BASE_URL + "color_list";
    public static final String GET_IMAGE_INFO = BASE_URL + "work_info";
    public static final String GET_USER_INFO = BASE_URL + "user_info";
    public static final String DELETE_DESIGN = BASE_URL + "del_work";
    public static final String DELETE_CARD = BASE_URL + "del_color";
    public static final String UPDATE_IMAGE_INFO = BASE_URL + "work_info";
    public static final String UPDATE_CARD_INFO = BASE_URL + "color_info";
    public static final String SUBMIT_IMAGEE_INFO = BASE_URL + "work";
    public static final String SUBMIT_CARD_INFO = BASE_URL + "color";
    public static final String APP_UPDATE_CHECK = BASE_URL + "update";
    public static final String REGISTER = BASE_URL + "qq_login";
    public static final String REGISTER_QQ_V2 = BASE_URL + "qq_login_v2";
    public static final String QQ_UNIONID = "https://graph.qq.com/oauth2.0/me?unionid=1&access_token=";
    public static final String REGISTER_WECHAT = BASE_URL + "wx_login";

    /**
     * 动态列表
     */
    public static final String JK_API_DISCOVER_THEME = BASE_URL + "discover_theme";
    /**
     * 话题详情
     */
    public static final String JK_API_THEME_TOPIC = BASE_URL + "theme_topic";
    /**
     * 发布话题
     */
    public static final String JK_API_POST_THEME = BASE_URL + "theme";
    /**
     * 动态评论
     */
    public static final String JK_API_DISCOVER_THEME_COMMENT = BASE_URL + "theme_comment";
    /**
     * 动态评论对应的回复
     */
    public static final String JK_API_DISCOVER_THEME_COMMENT_REPLYS = BASE_URL + "theme_reply";
    /**
     * 动态点赞
     */
    public static final String JK_API_DISCOVER_THEME_LIKE = BASE_URL + "theme_like";
    /**
     * 动态曝光
     */
    public static final String JK_API_DISCOVER_THEME_READ = BASE_URL + "theme_read";
    /**
     * 评论点赞
     */
    public static final String JK_API_DISCOVER_COMMENT_LIKE = BASE_URL + "comment_like";
    /**
     * 回复点赞
     */
    public static final String JK_API_DISCOVER_REPLY_LIKE = BASE_URL + "reply_like";
    /**
     * 删除评论\回复
     */
    public static final String JK_API_DISCOVER_DEL_COMMENT = BASE_URL + "del_comment";

    // 在线样机列表
    public static final String GET_ONLINE_AFFECT_LIST = BASE_URL + "jk_template";
    // 在线样机详情
    public static final String GET_ONLINE_AFFECT_DETAIL = BASE_URL + "template";
    // 在线色卡
    public static final String GET_ONLINE_COLOR_LIST = BASE_URL + "jk_color";
    // 在线教程
    public static final String GET_ONLINE_GUIDE_LIST = BASE_URL + "jk_teach";

    //活动投稿
    public static final String APPLY_PUBLISHING = BASE_URL + "collect";
    public static final String CANCEL_PUBLISHING = BASE_URL + "cancel_collect";

    public static final String HOME_ACTIVITY = BASE_URL + "discover_activity";
    public static final String HOME_PUBLISHING = BASE_URL + "discover_collect";

    /**
     * 我的页面 动态列表
     */
    public static final String JK_API_MY_NEWS = BASE_URL + "user_theme";

    /**
     * 我的页面 喜欢列表
     */
    public static final String JK_API_MY_LIKE = BASE_URL + "user_like";

    /**
     * 作品是否仅自己可见
     */
    public static final String JK_API_MY_GRID_VISIBLE = BASE_URL + "user_privacy";

    /**
     * 添加好友
     */
    // 请求推荐的好友
    public static final String GET_RECOMMEND_FRIEND = BASE_URL + "user_recommend";
    // 搜索好友
    public static final String SEARCH_FRIEND = BASE_URL + "user_search?size=50&page=";
    //关注列表
    public static final String GET_FOLLOW_LIST = BASE_URL + "follow";
    //粉丝列表
    public static final String GET_FOLLOER_LIST = BASE_URL + "fans";

    /**
     * 动态详情
     */
    public static final String JK_API_THEME = BASE_URL + "theme";
    /**
     * 关注功能
     */
    public static final String JK_API_THEME_FOLLOW = BASE_URL + "follow";
    /**
     * 删除动态
     */
    public static final String JK_API_THEME_DEL = BASE_URL + "del_theme";
    /**
     * 动态关注列表
     */
    public static final String JK_API_DYNAMIC_HTEME_FOLLOW = BASE_URL + "theme_follow";
    /**
     * 举报
     */
    public static final String JK_API_REPORT = BASE_URL + "report";

    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String truncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;

        strURL = strURL.trim().toLowerCase();

        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }

        return strAllParam;
    }

    /**
     * 解析出url参数中的键值队
     * 如 "www.xx.int?Action=int&id12345"，解析出Action:int,id:12345存入map中
     *
     * @param URL url地址
     * @return url请求参数部分
     */
    public static Map<String, String> parseParams(String URL) {
        Map<String, String> mapRequest = new HashMap<String, String>();

        String[] arrSplit = null;

        String strUrlParam = truncateUrlPage(URL);
        if (strUrlParam == null) {
            return mapRequest;
        }
        //每个键值为一组
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");

            //解析出键值
            if (arrSplitEqual.length > 1) {
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            } else {
                if (arrSplitEqual[0] != "") {
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

}

