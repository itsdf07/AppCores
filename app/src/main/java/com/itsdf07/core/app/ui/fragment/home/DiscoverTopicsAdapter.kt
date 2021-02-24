import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.itsdf07.core.app.R
import com.itsdf07.core.app.common.adapter.BaseRecyclerViewAdapter
import com.itsdf07.core.app.common.adapter.BaseViewHolder
import com.itsdf07.core.app.ui.fragment.home.DiscoverFragment
import com.itsdf07.core.app.ui.fragment.home.bean.TopicsBean

/**
 * @Description: 发现页头部热门话题（topics）对应适配器
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/20
 */
class DiscoverTopicsAdapter(
    var fragment: DiscoverFragment,
    var datas: List<TopicsBean>,
    layout: Int
) : BaseRecyclerViewAdapter<TopicsBean>(fragment.requireContext(), datas, layout) {

    override fun getItemCount(): Int {
        if (datas != null && datas.size > 10) {
            return 10
        }
        return super.getItemCount()
    }

    override fun bindData(
        holder: BaseViewHolder,
        data: TopicsBean,
        position: Int
    ) {
        var avatarItem: ImageView = holder.getView(R.id.iv_item_hot_avatar)
        var title: TextView = holder.getView(R.id.tv_hot_title)
        var info: TextView = holder.getView(R.id.tv_hot_info)
        title.text = data.name
        info.text = "${data.num} ${data.tag}"
        Glide.with(context)
            .load(data.icon)
            .apply(
                RequestOptions.circleCropTransform()
            )
            .into(avatarItem)
        holder.itemView.setOnClickListener {

        }
    }
}