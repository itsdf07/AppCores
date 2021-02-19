import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.itsdf07.core.app.R
import com.itsdf07.core.app.common.adapter.BaseRecyclerViewAdapter
import com.itsdf07.core.app.common.adapter.BaseViewHolder
import com.itsdf07.core.app.common.utils.DeviceUtils
import com.itsdf07.core.app.jk.JKUrlParse
import com.itsdf07.core.app.ui.fragment.home.DiscoverFragment
import com.itsdf07.core.app.ui.fragment.home.bean.ActivitysBean
import com.itsdf07.core.lib.alog.ALog

/**
 * @Description: 首页-发现-吸顶部分大图区（activitys）对应适配器
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/20
 */
class DiscoverActivitysAdapter(
    var fragment: DiscoverFragment,
    var datas: List<ActivitysBean>,
    layout: Int
) : BaseRecyclerViewAdapter<ActivitysBean>(fragment.context, datas, layout) {

    override fun bindData(
        holder: BaseViewHolder,
        data: ActivitysBean,
        position: Int
    ) {
        var activitysItemIcon: ImageView = holder.getView(R.id.iv_item_activitys)
        Glide.with(context)
            .load(data.img)
            .into(activitysItemIcon)
        var params: IntArray = JKUrlParse.parseWidthAndHeightByUrl(data.img)
        var height = 160
        if (params != null) {
            //动态算出即将加载的素材高-宽比例，进行设置View的宽高（即Item的高度）
            var ratio = params[1].toFloat() / params[0].toFloat()
            var screenWidth =
                DeviceUtils.getScreenPx(fragment.requireContext())[0] - DeviceUtils.dp2px(
                    context,
                    30f
                )
            height = (screenWidth * ratio).toInt()
            ALog.v("width:${screenWidth},ratio:${ratio},height:${height}")
        }
        ALog.v("width:${holder.itemView.layoutParams.width}")
        holder.itemView.layoutParams.height = height
//        holder.itemView.setOnClickListener {
//            if (data.is_login == 1 && TextUtils.isEmpty(JKAppCacheCommon.APP_USER_AUTH)) {
//                (fragment.activity as MainActivity).showLoginChoice()
//            } else {
//                UrlProtocolParser.parse(context, data.url)
//            }
//        }
    }
}