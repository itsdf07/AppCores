import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.itsdf07.core.app.R
import com.itsdf07.core.app.common.adapter.BaseRecyclerViewAdapter
import com.itsdf07.core.app.common.adapter.BaseViewHolder
import com.itsdf07.core.app.ui.fragment.home.bean.JKRespHeanBean

/**
 * @Description: 发现页头部蛋蛋区（eggs）对应适配器
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/20
 */
class JKHeadBlocksAdapter(
    context: Context,
    var datas: List<JKRespHeanBean.DataBean.EggsBean>,
    layout: Int
) : BaseRecyclerViewAdapter<JKRespHeanBean.DataBean.EggsBean>(context, datas, layout) {

    override fun getItemCount(): Int {
        if (datas != null && datas.size > 10) {
            return 10
        }
        return super.getItemCount()
    }

    override fun bindData(
        holder: BaseViewHolder,
        data: JKRespHeanBean.DataBean.EggsBean,
        position: Int
    ) {
        var blockItemIcon: ImageView = holder.getView(R.id.iv_item_block_ic)
        Glide.with(context)
            .load(data.img)
            .placeholder(R.mipmap.main_ico_yangji)
            .into(blockItemIcon)
        var eggItemTitle: TextView = holder.getView(R.id.iv_item_block_title)
        eggItemTitle.text = data.title
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "跳转至${data.url}", Toast.LENGTH_SHORT).show()
        }
    }
}