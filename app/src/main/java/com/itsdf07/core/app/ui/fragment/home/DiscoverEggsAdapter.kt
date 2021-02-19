import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.itsdf07.core.app.R
import com.itsdf07.core.app.common.adapter.BaseRecyclerViewAdapter
import com.itsdf07.core.app.common.adapter.BaseViewHolder
import com.itsdf07.core.app.jk.JKAppCacheCommon
import com.itsdf07.core.app.ui.fragment.home.DiscoverFragment
import com.itsdf07.core.app.ui.fragment.home.bean.BannersBean
import com.itsdf07.core.app.ui.fragment.home.bean.EggsBean
import com.itsdf07.core.app.ui.fragment.home.bean.JKRespHeanBean

/**
 * @Description: 首页-发现-吸顶部分蛋蛋区（eggs）对应适配器
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/20
 */
class DiscoverEggsAdapter(
    var fragment: DiscoverFragment,
    datas: ArrayList<EggsBean>,
    layout: Int
) : BaseRecyclerViewAdapter<EggsBean>(fragment.requireContext(), datas, layout) {

    override fun bindData(
        holder: BaseViewHolder,
        data: EggsBean,
        position: Int
    ) {
        var eggItemIcon: ImageView = holder.getView(R.id.iv_item_egg_ic)
        Glide.with(context)
            .load(data.img)
            .placeholder(R.mipmap.all_img_card_default)
            .into(eggItemIcon)
        var eggItemTitle: TextView = holder.getView(R.id.iv_item_egg_title)
        eggItemTitle.text = data.title
        holder.itemView.setOnClickListener {
            if (data.isLogin == 1 && TextUtils.isEmpty(JKAppCacheCommon.APP_USER_AUTH)) {
                Toast.makeText(context, "当前需求登录，并且当前用户未登录", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "跳转", Toast.LENGTH_SHORT).show()
            }

        }
    }
}