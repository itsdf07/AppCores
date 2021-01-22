package com.itsdf07.core.app.ui.fragment.gallery

import android.content.Context
import android.widget.ImageView
import com.itsdf07.core.app.R
import com.itsdf07.core.app.common.adapter.BaseRecyclerViewAdapter
import com.itsdf07.core.app.common.adapter.BaseViewHolder

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/12/3
 */
class StaggeredGridAdapter(context: Context, pics: List<Int>, layout: Int) :
    BaseRecyclerViewAdapter<Int>(context, pics, layout) {
    override fun bindData(holder: BaseViewHolder, data: Int, position: Int) {
        var itemImg = holder.getView<ImageView>(R.id.iv_item_icon)
        itemImg.setImageResource(data)
    }
}