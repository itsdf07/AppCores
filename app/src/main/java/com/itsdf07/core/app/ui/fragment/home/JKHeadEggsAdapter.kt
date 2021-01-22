
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
class JKHeadEggsAdapter(
    context: Context,
    var datas: List<JKRespHeanBean.DataBean.EggsBean>,
    layout: Int
) : BaseRecyclerViewAdapter<JKRespHeanBean.DataBean.EggsBean>(context, datas, layout) {

    override fun getItemCount(): Int {
        if (datas != null && datas.size > 5) {
            return 5
        }
        return super.getItemCount()
    }

    override fun bindData(
        holder: BaseViewHolder,
        data: JKRespHeanBean.DataBean.EggsBean,
        position: Int
    ) {
        var eggItemIcon: ImageView = holder.getView(R.id.iv_item_egg_ic)
        Glide.with(context)
            .load(data.img)
            .placeholder(R.mipmap.main_ico_yangji)
            .into(eggItemIcon)
//        Glide.with(context)
//            .load(data.img)
//            .apply(
//                RequestOptions.circleCropTransform()
//                    //通过缓存键检查是否更新
//                    .placeholder(R.mipmap.main_ico_yangji)
//                    .error(R.mipmap.main_ico_yangji)
//            )
//            .into(eggItemIcon)
        var eggItemTitle: TextView = holder.getView(R.id.iv_item_egg_title)
        eggItemTitle.text = data.title
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "跳转至${data.url}", Toast.LENGTH_SHORT).show()
        }
    }
}