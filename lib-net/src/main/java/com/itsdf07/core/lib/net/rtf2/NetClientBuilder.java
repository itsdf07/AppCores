package com.itsdf07.core.lib.net.rtf2;


import com.itsdf07.core.lib.net.callback.*;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @Description: 提供给框架外使用时进行网络请求参数设置
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/5/30
 */

public class NetClientBuilder {
    /**
     * 表单提交方式时设置的网络请求参数内容
     */
    private HashMap<String, Object> mParams;
    /**
     * 网络请求的api接口，如 api/test/test2PostWithParam2
     */
    private String mUrl;
    private IRequest mRequest;
    private ISuccess mSuccess;
    private IFailure mFailure;
    private IError mError;
    private RequestBody mBody;

    NetClientBuilder() {

    }

    /**
     * 网络请求的api接口
     *
     * @param url 如 api/test/test2PostWithParam2
     * @return
     */
    public final NetClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    /**
     * 表单提交方式时设置的网络请求参数内容
     *
     * @param params
     * @return
     */
    public final NetClientBuilder params(HashMap<String, Object> params) {
        this.mParams = params;
        return this;
    }

    /**
     * 添加请求成功的回调
     *
     * @param success
     * @return
     */
    public final NetClientBuilder success(ISuccess success) {
        this.mSuccess = success;
        return this;
    }

    /**
     * 添加请求过程中的回调，比如请求开始、请求结束
     *
     * @param request
     * @return
     */
    public final NetClientBuilder request(IRequest request) {
        this.mRequest = request;
        return this;
    }

    /**
     * 添加属于网络请求错误返回时的回调，如服务器错误码的返回
     *
     * @param error
     * @return
     */
    public final NetClientBuilder error(IError error) {
        this.mError = error;
        return this;
    }

    /**
     * 添加属于网络请求失败返回时的回调，如没有网络的失败返回
     *
     * @param failure
     * @return
     */
    public final NetClientBuilder failure(IFailure failure) {
        this.mFailure = failure;
        return this;
    }

    /**
     * Json格式的请求数据
     * MediaType.parse("application/json;charset=UTF-8")
     *
     * @param raw
     * @return
     */
    public final NetClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(
                MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final NetClient build() {
        return new NetClient(mParams, mUrl, mRequest, mSuccess, mFailure, mError, mBody);
    }
}
