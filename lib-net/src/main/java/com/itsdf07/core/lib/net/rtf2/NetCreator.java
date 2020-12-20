package com.itsdf07.core.lib.net.rtf2;


import com.itsdf07.core.lib.net.ConfigKeys;
import com.itsdf07.core.lib.net.NetInit;
import com.itsdf07.core.lib.net.api.ApiService;
import com.itsdf07.core.lib.net.api.ApiService98;
import com.itsdf07.core.lib.net.interceptor.BodyInterceptor;
import com.itsdf07.core.lib.net.interceptor.HeadInterceptor;
import com.itsdf07.core.lib.net.interceptor.RetryInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Description: 实现与 Retrofit2 接口对接
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/4/2
 */

public final class NetCreator {
    /**
     * 产生一个全局的 Retrofit 客户端
     */
    private static final class RetrofitHolder {
        private static final String BASE_URL = NetInit.getConfiguration(ConfigKeys.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OKHttpHolder.OK_HTTP_CLIENT)//不使用默认的OK3，那么就自行重新设置设置okhttp
                .build();
    }

    /**
     * 自定义OK3的内容
     */
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 30;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(new HeadInterceptor())
                .addInterceptor(new BodyInterceptor())
                .addInterceptor(new RetryInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))//打印okhttp请求体日志
                .build();
    }

    //提供接口让调用者得到retrofit对象
    private static final class RestServiceHolder {
        private static final ApiService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(ApiService.class);
    }

    //提供接口让调用者得到retrofit对象
    private static final class RestServiceHolder98 {
        private static final ApiService98 REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(ApiService98.class);
    }

    /**
     * 获取对象
     */
    public static ApiService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    /**
     * 获取98业务对象
     */
    public static ApiService98 getRestService98() {
        return RestServiceHolder98.REST_SERVICE;
    }
}
