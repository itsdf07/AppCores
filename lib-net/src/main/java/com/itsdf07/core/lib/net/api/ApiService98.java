package com.itsdf07.core.lib.net.api;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * @Description: 网络访问业务接口
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/5/30
 */
public interface ApiService98 {
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
