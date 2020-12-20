package com.itsdf07.core.lib.net.api;


import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @Description: 网络访问业务接口
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/5/30
 */
public interface ApiService {
    /**
     * 简单的get接口（无参）请求
     *
     * @param url http://192.168.3.3:8080/api/test/test2GetNoParam
     * @return
     */
    @GET
    Call<ShortVideoResult> get(@Url String url);

    /**
     * 带参的get接口请求，如 http://192.168.3.3:8080/api/test/test2GetWithParam1?param1=77&param2=123456
     *
     * @param url    http://192.168.3.3:8080/api/test/test2GetWithParam1
     * @param params HashMap 存入的key-value
     * @return
     */
    @GET
    Call<ShortVideoResult> get(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * 表单请求方式
     *
     * @param url
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST
    Call<ShortVideoResult> post(@Url String url, @FieldMap Map<String, Object> params);

    /**
     * 原始数据数据请求，即非表单方式，如：Json格式
     *
     * @param url
     * @param body
     * @return
     */
    @POST
    Call<String> postRaw(@Url String url, @Body RequestBody body);

    /*-------------------------------------------------------------------------------------------------*/
    @FormUrlEncoded
    @POST
    Call<ShortVideoResult> load98ShortVideos(
            @Field("catId") String catId,
            @Field("userId") String userId,
            @Field("positionType") String positionType,
            @Field("mobileType") String mobileType,
            @Field("isCombine") String isCombine,
            @Field("channelId") String channelId,
            @Field("videoDuty") String videoDuty,
            @Field("lastCatId") String lastCatId,
            @Field("appVersion") String appVersion,
            @Field("permission") int permission
    );
    @FormUrlEncoded
    @POST
    Call<ShortVideoResult> load98ShortVideos(@Url String url, @FieldMap Map<String, Object> params);
}
