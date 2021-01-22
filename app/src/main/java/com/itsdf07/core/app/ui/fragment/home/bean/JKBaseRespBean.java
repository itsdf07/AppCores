package com.itsdf07.core.app.ui.fragment.home.bean;


import java.io.Serializable;

/**
 * @Description: JK net请求响应 Base 基础信息，
 * 用于接收 data 数据的 Bean 类都必须 extends 改类，并根据 data 内容进行扩展， "data":{} 与 "data":[] 两种数据结构
 * @Author Aso
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/6
 */
public class JKBaseRespBean implements Serializable {
    /**
     * errorcode : 0
     * message :
     */

    private int errorcode;
    private String message;

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
