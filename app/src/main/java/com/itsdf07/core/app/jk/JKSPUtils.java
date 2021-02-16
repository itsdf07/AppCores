package com.itsdf07.core.app.jk;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.itsdf07.core.app.MyApplication;

import static android.content.Context.MODE_PRIVATE;

/**
 * @Description: SharedPreferences 存储工具类
 * @Author itsdf07
 * @Date 2021/1/7
 */
public class JKSPUtils {
    public static class JKSPKey {
        /**
         * 水印文字：watermark 用户自行编辑的本地标识，暂时不与云端同步，当新用户（即本地无水印文字数据），则使用昵称
         */
        public static final String SP_KEY_USER_WATERMARK = "USER_INFO_NICKNAME";

        /**
         * 用户授权头:PIE 1.eaKaoObCAxz9WUF1cIeQZw
         */
        public static final String SP_KEY_USER_AUTH = "USER_AUTH_INFO";
        /**
         * 设备唯一标识，暂时使用友盟生成的token
         */
        public static final String SP_KEY_DEVICEID = "DEVICEID";
        /**
         * 用户ID:134985
         */
        public static final String SP_KEY_USER_ID = "USER_INFO_ID";

        /**
         * APP配置信息
         */
        public static final String SP_KEY_USER_APP_CONFIG = "KEY_CONFIG_RESPONSE_MODEL";
        /**
         *
         */
        public static final String SP_KEY_AVATAR = "USER_INFO_AVATAR";

        /**
         * QQ登录的信息:openID
         */
        public static final String SP_KEY_QQ_OPENID = "USER_INFO_OPEN_ID";
        /**
         * QQ登录的信息:access_token
         */
        public static final String SP_KEY_QQ_ACCESS_TOKEN = "USER_INFO_ACCESS_TOKEN";
        /**
         * QQ登录的信息:UNION_ID
         */
        public static final String SP_KEY_QQ_UNION_ID = "USER_INFO_UNION_ID";
        /**
         * 微信登录的信息:code
         */
        public static final String SP_KEY_WECHAT_CODE = "USER_INFO_WECHAT_CODE";

        /**
         * 首页发现模块头部数据：对应接口-discover_activity
         */
        public static final String SP_KEY_APP_HOME_DISCOVER = "APP_HOME_DISVOCER";
    }

    public static final String SHARED_PREFERENCES = "JK_SP_FILE";
    private SharedPreferences mSp;
    private static JKSPUtils mInstance;

    public static JKSPUtils getInstance() {
        if (mInstance == null) {
            synchronized (JKSPUtils.class) {
                if (mInstance == null) {
                    mInstance = new JKSPUtils();
                }
            }
        }
        return mInstance;
    }

    public JKSPUtils() {
        mSp = MyApplication.mContext.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
    }

    /**
     * 保存基本数据类型的数据
     *
     * @param key
     * @param value
     */
    public void saveValue(String key, Object value) {
        SharedPreferences.Editor editor = mSp.edit();
        if (!TextUtils.isEmpty(key) && value != null) {
            if (value instanceof String) {
                editor.putString(key, (String) value);
            } else if (value instanceof Integer) {
                editor.putInt(key, (Integer) value);
            } else if (value instanceof Long) {
                editor.putLong(key, (Long) value);
            } else if (value instanceof Float) {
                editor.putFloat(key, (Float) value);
            } else if (value instanceof Boolean) {
                editor.putBoolean(key, (Boolean) value);
            }
        }
        editor.commit();
    }

    public <T> T getValue(String key, Class<T> clazz) {
        if (!TextUtils.isEmpty(key) && clazz != null) {
            String className = clazz.getSimpleName();
            if ("String".equals(className)) {
                return (T) mSp.getString(key, "");
            } else if ("Integer".equals(className)) {
                return (T) Integer.valueOf(mSp.getInt(key, 0));
            } else if ("Long".equals(className)) {
                return (T) Long.valueOf(mSp.getLong(key, 0));
            } else if ("Float".equals(className)) {
                return (T) Float.valueOf(mSp.getFloat(key, 0));
            } else if ("Boolean".equals(className)) {
                return (T) Boolean.valueOf(mSp.getBoolean(key, false));
            }
        }

        return null;
    }

    //--------------------------------------------------------------------------------------------------------------------------
    public void save(String key, String value) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(key, value);
        editor.commit();

    }

    public void remove(String key) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.remove(key);
        editor.commit();
    }

    public String read(String key) {
        String info = mSp.getString(key, "");
        return info;
    }

    public void saveBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putBoolean(key, value);
        editor.commit();

    }

    public boolean readBoolean(String key) {
        boolean info = mSp.getBoolean(key, false);
        return info;
    }
}
