package com.itsdf07.core.app.ui.activity.dynamic

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.itsdf07.core.app.R
import com.itsdf07.core.app.common.adapter.BaseRecyclerViewAdapter
import com.itsdf07.core.app.common.adapter.BaseViewHolder
import com.itsdf07.core.app.common.utils.DateTimeUtils
import com.itsdf07.core.lib.alog.ALog

/**
 * @Description: 动态评论列表适配器
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/12/3
 */
class DynamicCommentAdapter(
    context: Context,
    var commentsData: ArrayList<DynamicCommentsBean.CommentsBean>,
    layout: Int
) :
    BaseRecyclerViewAdapter<DynamicCommentsBean.CommentsBean>(context, commentsData, layout) {

    override fun bindData(
        holder: BaseViewHolder,
        data: DynamicCommentsBean.CommentsBean,
        position: Int
    ) {
        ALog.vTag("DynamicCommentAdapter", "data.comment_id=${data.comment_id}")
        var commentAvatar: ImageView = holder.getView(R.id.comment_avatar)
        var commentDisplayName: TextView = holder.getView(R.id.comment_display_name)
        var commentLike: ImageView = holder.getView(R.id.comment_like)
        var commentContent: TextView = holder.getView(R.id.comment_content)
        var commentDate: TextView = holder.getView(R.id.comment_date)
        var commentReplyMoreTip: TextView = holder.getView(R.id.comment_replay_more)
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
        if (data.reply_num > 0) {
            var dynamicDetailViewModel: DynamicDetailViewModel =
                ViewModelProvider(context as AppCompatActivity).get(DynamicDetailViewModel::class.java)
            var replys: RecyclerView = holder.getView(R.id.comment_replay)
            replys.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            ALog.vTag(
                "DynamicDetailViewModel",
                "comment_id:${data.comment_id},replys.size:${data.replys.size}"
            )
            var replysAdatper = DynamicCommentReplysAdapter(
                context,
                data.replys,
                R.layout.dynamic_comment_reply_item
            )
            commentReplyMoreTip.visibility = View.VISIBLE
            replys.adapter = replysAdatper
            dynamicDetailViewModel.netNotifyLifeData.observe(
                context as DynamicDetailActivity,
                Observer {
                    if (it.code == 0 && it.requestUrl == data.comment_id.toString()) {
                        replysAdatper.setData(data.replys, true)
                        replysAdatper.notifyDataSetChanged()
                    }

                })
            commentReplyMoreTip.setOnClickListener {
                dynamicDetailViewModel.loadMoreReplys(data.comment_id, 1)
            }
        } else {
            commentReplyMoreTip.visibility = View.GONE
        }
    }

}