import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itsdf07.core.app.R
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
    private val listMutableLiveData: MutableLiveData<ArrayList<ShortVideoResult.DataBean.FirstVideosVosBean>> =
        MutableLiveData()
    private var shortVideoList = arrayListOf<ShortVideoResult.DataBean.FirstVideosVosBean>()
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    var shortVideoCover = intArrayOf(
        R.mipmap.h_pic_1,
        R.mipmap.h_pic_2,
        R.mipmap.h_pic_3,
        R.mipmap.h_pic_4,
        R.mipmap.h_pic_5,
        R.mipmap.h_pic_6,
        R.mipmap.h_pic_7
    )

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

//    private fun initShortVideoData(): ArrayList<ShortVideoBean> {
//        for (i in 1..20) {
//            shortVideoList.add(
//                ShortVideoBean(
//                    "这是第" + i + "个端视频",
//                    "这个是端视频的详细描述",
//                    shortVideoCover = shortVideoCover[(shortVideoCover.indices).random()]
//                )
//            )
//        }
//        return shortVideoList
//    }
}