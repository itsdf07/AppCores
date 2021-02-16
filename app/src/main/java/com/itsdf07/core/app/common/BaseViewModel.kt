package com.itsdf07.core.app.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.itsdf07.core.app.ui.fragment.home.bean.JKBaseRespBean
import com.itsdf07.core.lib.alog.ALog

/**
 * @Description: MVVM中的VM超类，关心
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/7
 */
open class BaseViewModel : ViewModel() {
    /**
     * @Description: 通知相关与LifeData无直接关联的数据更新
     * @Author itsdf07
     * @E-Mail 923255742@qq.com
     * @Github https://github.com/itsdf07
     * @Date 2021/1/7
     */
    data class Notify2UILifeDataBean(
        var code: Int = -1,
        var msg: String = "",
        var requestUrl: String = ""
    )

    companion object {
        /**
         * net请求failure异常
         */
        const val API_FAILURE = -1

        /**
         * net请求error异常
         */
        const val API_ERROR = 2

        /**
         * 用户隐私，无权限
         */
        const val API_PRIVACY = 2001

    }

    /**
     * 用于网络请求时，相关的UI线程通知驱动
     */
    var netNotifyLifeData: MutableLiveData<Notify2UILifeDataBean> = MutableLiveData()

    /**
     * 处理 net 的响应异常处理
     * @param response 请求响应的json内容
     * @param tag 本次请求的标识，正常以url做标识（host+api）
     * @return false-没有异常（请求成功），true-请求失败（包括请求解析异常、业务码失败等）
     */
    @JvmOverloads
    fun filterErrorResponse(response: String?, tag: String = ""): Boolean {

        try {
            var gson = Gson()
            var baseResponse: JKBaseRespBean = gson.fromJson(response, JKBaseRespBean::class.java)
            if (baseResponse == null) {
                netNotifyLifeData.value = Notify2UILifeDataBean(API_ERROR, "服务器异常，请稍候重试", tag)
                return true
            }
            if (baseResponse.errorcode != 0) {
                ALog.eTag("JK_OKHTTP", "url:${tag}msg:${baseResponse.message}")
                netNotifyLifeData.value =
                    Notify2UILifeDataBean(baseResponse.errorcode, baseResponse.message, tag)
                return true
            }
        } catch (e: Exception) {
            netNotifyLifeData.value = Notify2UILifeDataBean(API_FAILURE, "网络异常，请检测网络", tag)
            return true
        }

        return false
    }

}