import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.itsdf07.core.app.R
import com.itsdf07.core.app.common.adapter.BaseRecyclerViewAdapter
import com.itsdf07.core.app.common.adapter.BaseViewHolder
import com.itsdf07.core.lib.net.api.ShortVideoResult
import java.util.ArrayList

/**
 * @Description: 短视频列表适配器
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/11/15
 */
class ShortVideoAdapter(
    context: Context,
    datas: ArrayList<ShortVideoResult.DataBean.FirstVideosVosBean>,
    layoutId: Int
) :
    BaseRecyclerViewAdapter<ShortVideoResult.DataBean.FirstVideosVosBean>(
        context,
        datas,
        layoutId
    ) {
    var mContext: Context = context;

    override fun bindData(
        holder: BaseViewHolder,
        data: ShortVideoResult.DataBean.FirstVideosVosBean,
        position: Int
    ) {
        var title = holder.getView<TextView>(R.id.text_shortvideo_title)
        var desc = holder.getView<TextView>(R.id.text_shortvideo_desc)
        var cover = holder.getView<ImageView>(R.id.bg_shortvideo_cover)
        title!!.text = data.video.title
        desc!!.text = data.video.videoAuthor
//        val drawable =
//            ResourcesCompat.getDrawable(mContext.resources, data.shortVideoCover, null)
//        cover!!.background = mContext.resources.getDrawable(data.shortVideoCover, null)
        Glide.with(mContext).load(data.video.bsyImgUrl).into(cover);
        holder.rootView!!.setOnClickListener {
            if (onItemClickListner != null) {
                onItemClickListner!!.onItemClickListner(holder.rootView, position)
            }
        }
    }
}