package com.itsdf07.core.lib.net.interceptor;


import com.itsdf07.core.lib.net.log.LogUtils;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/11/3
 */
public class HeadInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
//        final Request.Builder builder = chain.request().newBuilder();
//        builder.addHeader("version","1.0.1");
//        builder.addHeader("appOs","1");
//        return chain.proceed(builder.build());
//----------------------------------------------------------------------------------
        Request request = chain.request();
        LogUtils.logi("HeadInterceptor->intercept->请求url：" + request.url().host());

        Headers newHeader = null;
        //核心也是通过newBuilder 拿到Builder
        Headers.Builder oldHeaders = request.
                headers().
                newBuilder();
        //统一追加header参数
        newHeader = oldHeaders
                .add("version", "1.0.1")
                .add("appOs", "1")
                .build();

        Request newRequest = request.newBuilder()
                .headers(newHeader)
                .build();
        Response response = chain.proceed(newRequest);
        LogUtils.logi("HeadInterceptor->intercept->response.code：" + response.code());
        return response;
    }
}
