package com.itsdf07.core.app.ui.activity.dynamic

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.itsdf07.core.app.R
import com.itsdf07.core.app.common.adapter.BaseRecyclerViewAdapter
import com.itsdf07.core.app.common.adapter.BaseViewHolder
import com.itsdf07.core.app.common.utils.DateTimeUtils

/**
 * @Description: 动态评论列表适配器
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/12/3
 */
class DynamicCommentAdapter(
    context: Context,
    pics: List<DynamicCommentsBean.CommentsBean>,
    layout: Int
) :
    BaseRecyclerViewAdapter<DynamicCommentsBean.CommentsBean>(context, pics, layout) {

    override fun bindData(
        holder: BaseViewHolder,
        data: DynamicCommentsBean.CommentsBean,
        position: Int
    ) {
        var commentAvatar: ImageView = holder.getView(R.id.comment_avatar)
        var commentDisplayName: TextView = holder.getView(R.id.comment_display_name)
        var commentLike: ImageView = holder.getView(R.id.comment_like)
        var commentContent: TextView = holder.getView(R.id.comment_content)
        var commentDate: TextView = holder.getView(R.id.comment_date)
        var commentReplayMoreTip: TextView = holder.getView(R.id.comment_replay_more)
        Glide.with(context)
            .load(data.user_avatar)
            .apply(
                RequestOptions.circleCropTransform()
                    //通过缓存键检查是否更新
                    .placeholder(R.mipmap.icon_logo)
                    .error(R.mipmap.icon_logo)
            )
            .into(commentAvatar)

        commentDisplayName.text = data.user_name
        if (data.is_like == 0) {
            commentLike.setImageResource(R.mipmap.square_like_s_normal)
        } else {
            commentLike.setImageResource(R.mipmap.square_like_s_actived)
        }

        commentContent.text = data.content
        commentDate.text = DateTimeUtils.getDateToString(data.created_time, 14)
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "我被点击了$position", Toast.LENGTH_SHORT).show()
        }
        if (data.reply_num > 0) {
            commentReplayMoreTip.visibility = View.VISIBLE
            commentReplayMoreTip.setOnClickListener {
                Toast.makeText(context, "查看更多评论被点击了$position", Toast.LENGTH_SHORT).show()
            }
        } else {
            commentReplayMoreTip.visibility = View.GONE
        }

        commentLike.setOnClickListener {
            Toast.makeText(context, "点赞被点击了$position", Toast.LENGTH_SHORT).show()
        }


    }

}