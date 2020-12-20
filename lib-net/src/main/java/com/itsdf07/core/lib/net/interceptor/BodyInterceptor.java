package com.itsdf07.core.lib.net.interceptor;

import android.text.TextUtils;


import com.itsdf07.core.lib.net.log.LogUtils;
import com.itsdf07.core.lib.net.uitls.HostUtils;

import java.io.IOException;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
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
public class BodyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        LogUtils.logi("BodyInterceptor->intercept->请求url：" + request.url());
        if (request.method().equals("GET")) {
            HttpUrl.Builder builder = request.url().newBuilder();
            HttpUrl httpUrlurl = request.url();
            // 获取所有get参数
            Set<String> parameterNames = httpUrlurl.queryParameterNames();
            for (String key : parameterNames) {  //循环参数列表，如果参数的key不为空且是指定的参数，则进行拦截移除，之后可在根据需要是否重置数据
                if (!TextUtils.isEmpty(key) && key.contains("param1")
                        || !TextUtils.isEmpty(key) && key.contains("param2")) {
                    builder.removeAllQueryParameters(key);
                }
            }
            //增加公共参数
            HttpUrl newUrl = builder
//                    .addEncodedQueryParameter("param1", "这是param1新数据")
                    .addQueryParameter("param1", "这是param1新数据")
                    .addQueryParameter("param2", "这是param2新数据")
                    .build();


            request = request.newBuilder().url(newUrl).build();
        } else if (request.method().equals("POST")) {
            if (request.body() instanceof FormBody) {//表达提交
                LogUtils.logi("BodyInterceptor->intercept->表单请求");
                FormBody.Builder bodyBuilder = new FormBody.Builder();
//                FormBody formBody = (FormBody) request.body();
//                //加密参数容器
//                for (int i = 0; i < formBody.size(); i++) {
//                    //有的接口是无参的，@FormUrlEncoded必须要有一个参数，传一个空值请求
//                    //接口不允许参数为空值，空值签名会异常，所以空值不参与加密
//                    //中文参数解码签名会错误，所以不encodeValue
//                    String value = formBody.value(i);
//                    String name = formBody.name(i);
//                    if (!TextUtils.isEmpty(value) && name.contains("param1")) {
//                        continue;
//                    }
//                    bodyBuilder.addEncoded(formBody.encodedName(i), value);
//                }

                if ("xy.uheixia.com".equals(HostUtils.filterHost(request.url().host()))) {//98Debug
                    FormBody formBody = (FormBody) request.body();
                    //加密参数容器
                    for (int i = 0; i < formBody.size(); i++) {
                        //有的接口是无参的，@FormUrlEncoded必须要有一个参数，传一个空值请求
                        //接口不允许参数为空值，空值签名会异常，所以空值不参与加密
                        //中文参数解码签名会错误，所以不encodeValue
                        String value = formBody.value(i);
                        String name = formBody.name(i);
                        if (!TextUtils.isEmpty(value)) {
                            if (!TextUtils.isEmpty(name) && name.contains("appVersion")
                                    || !TextUtils.isEmpty(name) && name.contains("mobileType")
                                    || !TextUtils.isEmpty(name) && name.contains("appType")
                                    || !TextUtils.isEmpty(name) && name.contains("channelId")
                                    || !TextUtils.isEmpty(name) && name.contains("appPackage")
                                    || !TextUtils.isEmpty(name) && name.contains("deviceId")
//                                || !TextUtils.isEmpty(name) && name.contains("phone")
                                    || !TextUtils.isEmpty(name) && name.contains("userId")
                                    || !TextUtils.isEmpty(name) && name.contains("token")) {
                                continue;
                            }
                            bodyBuilder.addEncoded(formBody.encodedName(i), value);
                        }
                    }
                    //增加公共参数
                    bodyBuilder.addEncoded("appVersion", "3.2.12");
                    bodyBuilder.addEncoded("mobileType", "2");
                    bodyBuilder.addEncoded("appType", "xysp");
                    bodyBuilder.addEncoded("channelId", "xysp_yingyongbao");
                    bodyBuilder.addEncoded("appPackage", "com.mg.xyvideo");
                    bodyBuilder.addEncoded("deviceId", "12d86812");
                    bodyBuilder.addEncoded("userId", "0");
                    bodyBuilder.addEncoded("token", "");
                    // 穿山甲、广点通、快手SDK版本
                    bodyBuilder.addEncoded("csjSdkVersion", "3.3.0.0");
                    bodyBuilder.addEncoded("gdtSdkVersion", "4.290.1160");
                    bodyBuilder.addEncoded("ksSdkVersion", "3.3.7.2");
                } else {
                    //增加公共参数
                    bodyBuilder.add("param1", "这是param1新数据");
                    bodyBuilder.add("param2", "这是param2新数据");
                }
                FormBody formBody = bodyBuilder.build();
                request = request.newBuilder().post(formBody).build();
            }
//            else if (request.body() instanceof RequestBody) {
//                ALog.dTag("OkHttp", "原始数据数据请求");
//                RequestBody requestBody = request.body();
//                Buffer buffer = new Buffer();
//                requestBody.writeTo(buffer);
//                ALog.dTag("okHttp", "body:%s", buffer.readByteString().utf8());
//            }
        }
        Response response = chain.proceed(request);
        LogUtils.logi("BodyInterceptor->intercept->response.code：" + response.code());
        return response;
    }
}
