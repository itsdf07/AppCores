package com.itsdf07.core.app.ui.activity.dynamic;

import java.io.Serializable;

/**
 * @Description: 图片轮播器数据实体类
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/23
 */
public class ImagesSlideBean implements Serializable {
    /**
     * id : 3
     * img_url : https://static.imjk.club/template/1609927938986.png?1035_1035
     */

    /**
     * 图片唯一表示
     */
    private int id;
    /**
     * 图片对应线上地址
     */
    private String imgUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
