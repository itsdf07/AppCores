import android.content.Intent
import android.text.TextUtils
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.itsdf07.core.app.R
import com.itsdf07.core.app.common.adapter.BaseRecyclerViewAdapter
import com.itsdf07.core.app.common.adapter.BaseViewHolder
import com.itsdf07.core.app.jk.JKAppCacheCommon
import com.itsdf07.core.app.ui.activity.dynamic.DynamicDetailActivity
import com.itsdf07.core.app.ui.fragment.home.DiscoverFragment
import com.itsdf07.core.app.ui.fragment.home.bean.BannersBean

/**
 * @Description: 首页-发现-吸顶部分金刚区（banners）对应适配器
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/20
 */
class DiscoverBannersAdapter(
    var fragment: DiscoverFragment,
    datas: ArrayList<BannersBean>,
    layout: Int
) : BaseRecyclerViewAdapter<BannersBean>(fragment.requireContext(), datas, layout) {

    override fun bindData(
        holder: BaseViewHolder,
        data: BannersBean,
        position: Int
    ) {
        var bannerItem: ImageView = holder.getView(R.id.iv_item_banners)
        Glide.with(context)
            .load(data.img)
            .placeholder(R.mipmap.all_img_card_default)
            .into(bannerItem)
        holder.itemView.setOnClickListener {
            if (data.isLogin == 1 && TextUtils.isEmpty(JKAppCacheCommon.APP_USER_AUTH)) {
                Toast.makeText(context, "当前需求登录，并且当前用户未登录", Toast.LENGTH_SHORT).show()
            } else {
                context.startActivity(Intent(context, DynamicDetailActivity::class.java))
            }
        }
    }
}