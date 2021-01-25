import android.content.Context
import android.content.Intent
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.itsdf07.core.app.R
import com.itsdf07.core.app.common.adapter.BaseRecyclerViewAdapter
import com.itsdf07.core.app.common.adapter.BaseViewHolder
import com.itsdf07.core.app.ui.activity.dynamic.DynamicDetailActivity
import com.itsdf07.core.app.ui.fragment.home.bean.JKRespHeanBean

/**
 * @Description: 发现页头部金刚区（banners）对应适配器
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/20
 */
class JKHeadBannersAdapter(
    context: Context,
    var datas: List<JKRespHeanBean.DataBean.BannersBean>,
    layout: Int
) : BaseRecyclerViewAdapter<JKRespHeanBean.DataBean.BannersBean>(context, datas, layout) {

    override fun getItemCount(): Int {
        if (datas != null && datas.size > 10) {
            return 10
        }
        return super.getItemCount()
    }

    override fun bindData(
        holder: BaseViewHolder,
        data: JKRespHeanBean.DataBean.BannersBean,
        position: Int
    ) {
        var bannerItem: ImageView = holder.getView(R.id.iv_item_banners)
        Glide.with(context)
            .load(data.img)
            .placeholder(R.mipmap.main_img_creation)
            .into(bannerItem)
        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, DynamicDetailActivity::class.java))
//            Toast.makeText(context, "跳转至${data.url}", Toast.LENGTH_SHORT).show()
        }
    }
}