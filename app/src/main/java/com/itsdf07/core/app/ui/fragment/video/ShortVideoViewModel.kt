package com.itsdf07.core.app.ui.fragment.video

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itsdf07.core.lib.alog.ALog
import com.itsdf07.core.lib.net.api.ShortVideoResult
import com.itsdf07.core.lib.net.callback.IError
import com.itsdf07.core.lib.net.callback.IFailure
import com.itsdf07.core.lib.net.callback.ISuccess
import com.itsdf07.core.lib.net.rtf2.NetClient
import java.util.*

/**
 * @Description: 短视频数据
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/11/14
 */
class ShortVideoViewModel : ViewModel() {
    public val listMutableLiveData: MutableLiveData<ArrayList<ShortVideoResult.DataBean.FirstVideosVosBean>> =
        MutableLiveData()
    private var shortVideoList = arrayListOf<ShortVideoResult.DataBean.FirstVideosVosBean>()

    fun loadData(): MutableLiveData<ArrayList<ShortVideoResult.DataBean.FirstVideosVosBean>> {
        listMutableLiveData.value = shortVideoList
        val requParam = HashMap<String, Any>()
        requParam["catId"] = "0"
        requParam["userId"] = "0"
        requParam["positionType"] = "personalPage"
        requParam["mobileType"] = "2"
        requParam["isCombine"] = "2"
        requParam["channelId"] = "xysp_yingyongbao"
        requParam["videoDuty"] = "3,4,1"
        requParam["lastCatId"] = ""
        requParam["appVersion"] = "3.2.20"
        requParam["permission"] = 1
        NetClient.create().url("https://xy.uheixia.com/api/video/firstRecommendVideosList/1.8")
            .params(requParam)
            .success(object : ISuccess<ShortVideoResult> {
                override fun onSuccess(response: ShortVideoResult) {
                    ALog.dTag(
                        "98Test",
                        "98 request success->response:%s",
                        response.data.firstVideosVos.size
                    )
                    listMutableLiveData.value!!.addAll(response.data.firstVideosVos)
                    listMutableLiveData.postValue(listMutableLiveData.value)

                }
            }).failure(object : IFailure {
                override fun onFailure() {
                    ALog.dTag("98Test", "98 request failure->onFailure")
                }
            }).error(object : IError {
                override fun onError(code: Int, msg: String?) {
                    ALog.dTag("98Test", "98 request error->code:%s,msg:%s", code, msg)
                }
            }).build().load98ShortVideosPost()
        return listMutableLiveData
    }
}