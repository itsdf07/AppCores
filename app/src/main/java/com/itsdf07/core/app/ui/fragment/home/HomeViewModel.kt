import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itsdf07.core.app.ui.fragment.home.bean.JKRespHeanBean

class HomeViewModel : ViewModel() {

    /**
     * 可读写发现头部数据
     */
    private val _headBeanData = MutableLiveData<JKRespHeanBean.DataBean>().apply {
        value = simulateHeadData()
    }

    /**
     * 仅可读发现头部数据，对外使用，用于UI驱动
     */
    val headBeanData: LiveData<JKRespHeanBean.DataBean> = _headBeanData

    init {

    }

    //--------------------------------- 数据模拟
    /**
     * 模拟发现页头部数据
     */
    fun simulateHeadData(): JKRespHeanBean.DataBean {
        var headBean = JKRespHeanBean()
        headBean.errorcode = 0
        headBean.message = ""
        var dataBean = JKRespHeanBean.DataBean()
        dataBean.banners = simulateHead_banners()
        dataBean.eggs = simulateHead_eggs()
        dataBean.activitys = simulateHead_activitys()
        headBean.data = dataBean
        return headBean.data
    }

    /**
     *  模拟发现页头部金刚区（banners）列表数据，如创作、取色块-->
     */
    private fun simulateHead_banners(): ArrayList<JKRespHeanBean.DataBean.BannersBean> {
        var bannersBeanList = arrayListOf<JKRespHeanBean.DataBean.BannersBean>()
        bannersBeanList.add(JKRespHeanBean.DataBean.BannersBean().apply {
            id = 1
            img = "http://static.imjk.club/banner/1606383859810.png?501_240"
            url =
                "imjk://app/navigate?params=JTdCJTIydGFyZ2V0JTIyOiUyMmNyZWF0ZWdyaWQlMjIsJTIyb3B0aW9ucyUyMjolN0IlN0QlN0Q="
            is_login = 0
        })
        bannersBeanList.add(JKRespHeanBean.DataBean.BannersBean().apply {
            id = 2
            img = "http://static.imjk.club/banner/1606383988410.png?501_240"
            url =
                "imjk://app/navigate?params=JTdCJTIydGFyZ2V0JTIyOiUyMmNyZWF0ZWNvbG9yJTIyLCUyMm9wdGlvbnMlMjI6JTdCJTdEJTdE"
            is_login = 0
        })

        return bannersBeanList
    }


    /**
     *  模拟发现页头部蛋蛋区（banners）列表数据，如样机、色卡、教程块-->
     */
    private fun simulateHead_activitys(): ArrayList<JKRespHeanBean.DataBean.ActivitysBean> {
        var activitysBeanList = arrayListOf<JKRespHeanBean.DataBean.ActivitysBean>()
        activitysBeanList.add(JKRespHeanBean.DataBean.ActivitysBean().apply {
            id = 3
            img = "https://static.imjk.club/banner/1609237397254.png?1035_480"
            url = "https://static.imjk.club/gblxs/index.html?nav_alpha=0&simple_left=0"
            is_login = 0
            var broadcastsBeanList =
                arrayListOf<JKRespHeanBean.DataBean.ActivitysBean.BroadcastsBean>()
            broadcastsBeanList.add(JKRespHeanBean.DataBean.ActivitysBean.BroadcastsBean().apply {
                name = "JK设计师"
                avatar = "https://static.imjk.club/avatar/avatar_1611125157359.jpg"
                msg = "格子酱广播内容"
            })
            broadcasts = broadcastsBeanList
        })
        activitysBeanList.add(JKRespHeanBean.DataBean.ActivitysBean().apply {
            id = 4
            img = "http://static.imjk.club/banner/1607662919857.png?1035_480"
            url =
                "http://mvvideo10.meitudata.com/5fc645e2de3a7vurdqojcq6006_H264_1_48487ee838d5a4.mp4"
            is_login = 0
            var broadcastsBeanList =
                arrayListOf<JKRespHeanBean.DataBean.ActivitysBean.BroadcastsBean>()
            broadcastsBeanList.add(JKRespHeanBean.DataBean.ActivitysBean.BroadcastsBean().apply {
                name = "JK设计师"
                avatar = "https://static.imjk.club/avatar/avatar_1611125157359.jpg"
                msg = "格子酱广播内容"
            })
            broadcasts = broadcastsBeanList
        })
        activitysBeanList.add(JKRespHeanBean.DataBean.ActivitysBean().apply {
            id = 6
            img = "http://static.imjk.club/banner/1607662908989.png?1035_480"
            url =
                "http://mvvideo10.meitudata.com/5fc89d4f2468d23sy5nd6c7261_H264_1_48d40b937be184.mp4"
            is_login = 0
            var broadcastsBeanList =
                arrayListOf<JKRespHeanBean.DataBean.ActivitysBean.BroadcastsBean>()
            broadcastsBeanList.add(JKRespHeanBean.DataBean.ActivitysBean.BroadcastsBean().apply {
                name = "JK设计师"
                avatar = "https://static.imjk.club/avatar/avatar_1611125157359.jpg"
                msg = "格子酱广播内容"
            })
            broadcasts = broadcastsBeanList
        })
        activitysBeanList.add(JKRespHeanBean.DataBean.ActivitysBean().apply {
            id = 10
            img = "http://static.imjk.club/banner/1608178377372.png?1035_480"
            url =
                "http://mvvideo10.meitudata.com/5fdac60f9d629fz5puw20f483_H264_1_4d0e6cf2bf7b4f.mp4"
            is_login = 0
            var broadcastsBeanList =
                arrayListOf<JKRespHeanBean.DataBean.ActivitysBean.BroadcastsBean>()
            broadcastsBeanList.add(JKRespHeanBean.DataBean.ActivitysBean.BroadcastsBean().apply {
                name = "JK设计师"
                avatar = "https://static.imjk.club/avatar/avatar_1611125157359.jpg"
                msg = "格子酱广播内容"
            })
            broadcasts = broadcastsBeanList
        })
        return activitysBeanList
    }

    /**
     *  模拟发现页头部蛋蛋区（banners）列表数据，如样机、色卡、教程块-->
     */
    private fun simulateHead_eggs(): ArrayList<JKRespHeanBean.DataBean.EggsBean> {
        var eggsBeanList = arrayListOf<JKRespHeanBean.DataBean.EggsBean>()
        eggsBeanList.add(JKRespHeanBean.DataBean.EggsBean().apply {
            id = 14
            title = "样机"
            img = "http://static.imjk.club/banner/1608192107346.png?156_156"
            url =
                "https://www.imjk.club/web/test/02/#/prototype?nav_alpha=0&simple_left=0&canUse=false"
            is_login = 0
        })
        eggsBeanList.add(JKRespHeanBean.DataBean.EggsBean().apply {
            id = 15
            title = "色卡"
            img = "http://static.imjk.club/banner/1608192191778.png?156_156"
            url =
                "https://www.imjk.club/web/test/02/#/color-card?nav_alpha=0&simple_left=0"
            is_login = 0
        })
        eggsBeanList.add(JKRespHeanBean.DataBean.EggsBean().apply {
            id = 16
            title = "教程"
            img = "http://static.imjk.club/banner/1608192263874.png?156_156"
            url =
                "https://www.imjk.club/web/test/02/#/tutorials?nav_alpha=2"
            is_login = 0
        })
        return eggsBeanList
    }


}