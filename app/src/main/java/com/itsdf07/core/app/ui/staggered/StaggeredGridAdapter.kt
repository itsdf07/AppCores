
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.itsdf07.core.app.R
import com.itsdf07.core.app.common.adapter.BaseRecyclerViewAdapter
import com.itsdf07.core.app.common.adapter.BaseViewHolder
import com.itsdf07.core.app.ui.staggered.StaggeredGridBean


/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/20
 */
class StaggeredGridAdapter(context: Context, pics: ArrayList<StaggeredGridBean>, layout: Int) :
    BaseRecyclerViewAdapter<StaggeredGridBean>(context, pics, layout) {

    override fun bindData(holder: BaseViewHolder, data: StaggeredGridBean, position: Int) {
        var ivItemProduction = holder.getView<ImageView>(R.id.iv_item_icon)
        var tvItemContent: TextView = holder.getView(R.id.tv_item_content)
        var ivItemAvatar = holder.getView<ImageView>(R.id.iv_item_user_avatar)
        var tvItemDispName = holder.getView<TextView>(R.id.iv_item_user_dispname)

        ivItemProduction.setImageResource(data.localProduction)
//        Glide.with(context)
//            .load(data.localProduction)
//            .placeholder(R.mipmap.icon_logo)
//            .into(ivItemProduction)
        tvItemContent.text = data.content
//        ivItemAvatar.setImageResource(R.mipmap.icon_logo)
        Glide.with(context)
            .load(data.avatar)
            .apply(
                RequestOptions.circleCropTransform()
                    //通过缓存键检查是否更新
                    .placeholder(R.mipmap.icon_logo)
                    .error(R.mipmap.icon_logo)
            )
            .into(ivItemAvatar)
        tvItemDispName.text = data.dispName
    }


}