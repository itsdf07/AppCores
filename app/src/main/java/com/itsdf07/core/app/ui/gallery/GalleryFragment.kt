package com.itsdf07.core.app.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.itsdf07.core.app.R
import com.itsdf07.core.lib.alog.ALog
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener

class GalleryFragment : Fragment() {
    private lateinit var galleryViewModel: GalleryViewModel

    /**
     * 下拉刷新、上滑加载更多控件
     */
    private lateinit var refreshLayout: SmartRefreshLayout

    /**
     * 瀑布流图片数据展示列表
     */
    private lateinit var staggeredlPicList: RecyclerView

    /**
     * 瀑布流列表 RecyclerView 对应的适配器
     */
    private lateinit var staggeredGridAdapter: StaggeredGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        galleryViewModel.itemDatas.observe(viewLifecycleOwner, Observer {
            ALog.d("数据更新了${it}")
//            staggeredGridAdapter.notifyDataSetChanged()
            staggeredGridAdapter.notifyItemRemoved(0)
        })
        initView(root)
        return root
    }

    fun initView(root: View) {
        staggeredlPicList = root.findViewById(R.id.stattered_list)
        staggeredlPicList.itemAnimator = DefaultItemAnimator()
        staggeredlPicList.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        staggeredGridAdapter =
            context?.let {
                StaggeredGridAdapter(it, galleryViewModel.itemDatas.value!!, R.layout.item_saggered)
            }!!
        staggeredlPicList.adapter = staggeredGridAdapter

        refreshLayout = root.findViewById(R.id.refreshLayout)
        refreshLayout.setRefreshHeader(ClassicsHeader(context))
        refreshLayout.setRefreshFooter(ClassicsFooter(context))
        refreshLayout.setOnRefreshListener(onRefreshListener)
        refreshLayout.setOnLoadMoreListener(onLoadMoreListener)
    }

    private val onRefreshListener = OnRefreshListener {
        Toast.makeText(context, "下拉刷新", Toast.LENGTH_SHORT).show()
        refreshLayout.finishRefresh(2000)
    }
    private val onLoadMoreListener = OnLoadMoreListener {
        Toast.makeText(context, "上拉加载成功", Toast.LENGTH_SHORT).show()
        refreshLayout.finishLoadMore(2000)
    }
}