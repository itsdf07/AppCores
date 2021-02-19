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
import com.itsdf07.core.app.ui.fragment.home.bean.BlocksBean

/**
 * @Description: 发现页头部蛋蛋区（eggs）对应适配器
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/20
 */
class DiscoverBlocksAdapter(
    var fragment: DiscoverFragment,
    datas: ArrayList<BlocksBean>,
    layout: Int
) : BaseRecyclerViewAdapter<BlocksBean>(fragment.requireContext(), datas, layout) {

    override fun bindData(
        holder: BaseViewHolder,
        data: BlocksBean,
        position: Int
    ) {
        var blockItemIcon: ImageView = holder.getView(R.id.iv_item_block_ic)
        Glide.with(context)
            .load(data.img)
            .into(blockItemIcon)
        var eggItemTitle: TextView = holder.getView(R.id.iv_item_block_title)
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