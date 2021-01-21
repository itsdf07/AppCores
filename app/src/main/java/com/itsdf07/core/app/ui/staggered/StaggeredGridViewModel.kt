import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itsdf07.core.app.R
import com.itsdf07.core.app.ui.staggered.StaggeredGridBean

class StaggeredGridViewModel : ViewModel() {
    private var picRes = intArrayOf(
        R.mipmap.pic_3,
        R.mipmap.pic_1,
        R.mipmap.pic_5,
        R.mipmap.pic_4,
        R.mipmap.pic_1,
        R.mipmap.pic_2,
        R.mipmap.pic_6,
        R.mipmap.pic_4,
        R.mipmap.pic_2,
        R.mipmap.pic_1,
        R.mipmap.pic_5
    )
    private val _itemBean = MutableLiveData<ArrayList<StaggeredGridBean>>().apply {
        var itemDatas = arrayListOf<StaggeredGridBean>()
        for (i in picRes.indices) {
            itemDatas.add(StaggeredGridBean().apply {
                productionUrl = ""
                localProduction = picRes[i]
                avatar =
                    "http://thirdqq.qlogo.cn/g?b=oidb&k=o6E9micm9w2pY7K1uJIibysQ&s=100&t=1555830641"
                dispName = "叫我ASO"
                content =
                    "叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO叫我ASO"
                type = 1
                count = 77
            })
        }
        value = itemDatas
    }

    /**
     * LiveData对应瀑布流的Item数据
     */
    val itemBean: LiveData<ArrayList<StaggeredGridBean>> = _itemBean
}