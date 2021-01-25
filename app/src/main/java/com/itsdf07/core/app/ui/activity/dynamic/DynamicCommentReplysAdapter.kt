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
 * @Description: 动态评论列表中回复的适配器
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/12/3
 */
class DynamicCommentReplysAdapter(
    context: Context,
    var replysData: ArrayList<DynamicCommentReplysBean.ReplysBean>,
    layout: Int
) :
    BaseRecyclerViewAdapter<DynamicCommentReplysBean.ReplysBean>(context, replysData, layout) {
    /**
     * @param newCommentsData 追加的数据
     * @param isClean 是否需要清除旧数据
     */
    fun setData(
        newCommentReplysData: ArrayList<DynamicCommentReplysBean.ReplysBean>,
        isClean: Boolean
    ) {
        if (replysData == null) {
            replysData = arrayListOf()
        }
        if (isClean) {
            replysData.clear()
        }
        replysData.addAll(newCommentReplysData)
        notifyDataSetChanged()
    }

    fun getData(): ArrayList<DynamicCommentReplysBean.ReplysBean> {
        if (replysData == null) {
            return arrayListOf()
        }
        return replysData
    }

    override fun bindData(
        holder: BaseViewHolder,
        data: DynamicCommentReplysBean.ReplysBean,
        position: Int
    ) {
        var replyAvatar: ImageView = holder.getView(R.id.reply_avatar)
        var replyDisplayName: TextView = holder.getView(R.id.reply_display_name)
        var replyIsAuth: TextView = holder.getView(R.id.reply_is_auth)
        var replyIsAuthor: TextView = holder.getView(R.id.reply_is_author)
        var replyLike: ImageView = holder.getView(R.id.reply_like)

        var replyContent: TextView = holder.getView(R.id.reply_content)
        var replyDate: TextView = holder.getView(R.id.reply_date)

        Glide.with(context)
            .load(data.user_avatar)
            .apply(
                RequestOptions.circleCropTransform()
                    //通过缓存键检查是否更新
                    .placeholder(R.mipmap.icon_logo)
                    .error(R.mipmap.icon_logo)
            )
            .into(replyAvatar)

        replyDisplayName.text = data.user_name
        if (data.is_like == 0) {
            replyLike.setImageResource(R.mipmap.square_like_s_normal)
        } else {
            replyLike.setImageResource(R.mipmap.square_like_s_actived)
        }

        replyContent.text = data.content
        replyDate.text = DateTimeUtils.getDateToString(data.created_time, 14)

        replyLike.setOnClickListener {
            Toast.makeText(context, "点赞被点击了$position", Toast.LENGTH_SHORT).show()
        }
    }


}