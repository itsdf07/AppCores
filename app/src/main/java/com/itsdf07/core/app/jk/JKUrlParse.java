package com.itsdf07.core.app.jk;

import android.text.TextUtils;

/**
 * @Description: URL相关解析类
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/26
 */
public class JKUrlParse {

    /**
     * 解析出JK提供的url中的宽高
     *
     * @param url
     * @return 宽——高
     */
    public static int[] parseWidthAndHeightByUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        String[] parseUrl = url.split("\\?");
        if (parseUrl.length < 2) {
            return new int[]{450, 450};
        }
        String[] parseWidthAndHeight = parseUrl[1].split("_");
        if (parseWidthAndHeight.length != 2) {
            return new int[]{450, 450};
        }
        int[] params = new int[2];
        try {
            params[0] = Integer.parseInt(parseWidthAndHeight[0]);
            params[1] = Integer.parseInt(parseWidthAndHeight[1]);
            return params;
        } catch (NumberFormatException e) {
            return new int[]{450, 450};
        }
    }
}
