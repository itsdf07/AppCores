package com.itsdf07.core.app.ui.fragment.home

import DiscoverBannersAdapter
import DiscoverEggsAdapter
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.itsdf07.core.app.R
import com.itsdf07.core.app.common.UniversalItemDecoration
import com.itsdf07.core.app.common.utils.DeviceUtils
import com.itsdf07.core.app.jk.JKUrl
import com.itsdf07.core.app.ui.fragment.home.bean.BannersBean
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener

/**
 * @Description: 首页-发现
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/2/15
 */
class DiscoverFragment : Fragment() {

    private lateinit var discoverViewModel: DiscoverViewModel
    private lateinit var tabLayout: TabLayout
    lateinit var tabViewPage: ViewPager
    lateinit var rootView: View

    private val tabs = arrayOf("推荐", "最新")
    private val tabFragmentList = arrayListOf<Fragment>()

    /**
     * 下拉刷新、上滑加载更多控件
     */
    private lateinit var refreshLayout: SmartRefreshLayout

    /**
     * 头部金刚区
     */
    private lateinit var headBannersList: RecyclerView
    private lateinit var headBannersAdapter: DiscoverBannersAdapter
    /**
     * 头部蛋蛋区
     */
    private lateinit var headEggsList: RecyclerView
    private lateinit var headEggsAdapter: DiscoverEggsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_discover, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        discoverViewModel =
            ViewModelProvider(requireActivity()).get(DiscoverViewModel::class.java)
        // TODO: Use the ViewModel
        discoverViewModel.bannersBean.observe(requireActivity(), Observer {
            updateBannersUI(it)
        })

        discoverViewModel.netNotifyLifeData.observe(viewLifecycleOwner, Observer {
            when (it.requestUrl) {
                JKUrl.HOME_ACTIVITY -> {
                    refreshLayout.finishRefresh()
                }
            }

        })

        initAllView()
        discoverViewModel.requeryDiscover()
    }

    private fun initAllView() {
        refreshLayout = rootView.findViewById(R.id.refreshLayout)
        refreshLayout.setRefreshHeader(ClassicsHeader(context))
        refreshLayout.setOnRefreshListener(onRefreshListener)

        //金刚区相关
        headBannersList = rootView.findViewById(R.id.banners_list)
        headBannersList.addItemDecoration(object : UniversalItemDecoration() {
            override fun getItemOffsets(position: Int): Decoration? {
                val decoration = ColorDecoration()
                decoration.decorationColor = Color.TRANSPARENT;
                if (position < 2) {//列表的索引1和2，目的是绘制列表顶部分割线高度
                    decoration.top = DeviceUtils.dp2px(requireContext(), 20f)
                } else {
                    decoration.top = DeviceUtils.dp2px(requireContext(), 10f)
                }
                if (position % 2 == 0) {//第一行的第一列，通用的话可以使用position % 2 == 0
                    decoration.left = DeviceUtils.dp2px(requireContext(), 15f)
                    decoration.right = DeviceUtils.dp2px(requireContext(), 7f)
                } else {//第一行的第二列，通用的话可以使用position % 2 == 1
                    decoration.left = DeviceUtils.dp2px(requireContext(), 7f)
                    decoration.right = DeviceUtils.dp2px(requireContext(), 15f)
                }
                decoration.bottom = 0
                return decoration
            }
        })

        headBannersList.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        headBannersAdapter = DiscoverBannersAdapter(
            this,
            discoverViewModel.bannersBean.value ?: arrayListOf(),
            R.layout.jk_item_home_head_banner
        )
        headBannersList.adapter = headBannersAdapter

        //蛋蛋区

    }

    private fun updateBannersUI(banners: ArrayList<BannersBean>) {
        if (banners == null || banners.size == 0) {
            headBannersList.visibility = View.GONE
            return
        }
        headBannersList.visibility = View.VISIBLE
        headBannersAdapter.updateData(banners)

    }

    private val onRefreshListener = OnRefreshListener {
        refreshLayout.finishRefresh(5000)
        discoverViewModel.requeryDiscover()
    }
}