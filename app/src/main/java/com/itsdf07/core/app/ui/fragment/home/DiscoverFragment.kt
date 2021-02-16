package com.itsdf07.core.app.ui.fragment.home

import DiscoverBannersAdapter
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
import com.itsdf07.core.app.ui.fragment.home.bean.BannersBean

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
     * 头部金刚区
     */
    private lateinit var headBannersList: RecyclerView
    private lateinit var headBannersAdapter: DiscoverBannersAdapter


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
            updateBannersUI(it);
        })

        initAllView()
        discoverViewModel.requeryDiscover()
    }

    private fun initAllView() {
        headBannersList = rootView.findViewById(R.id.banners_list)
        headBannersList.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        headBannersAdapter = DiscoverBannersAdapter(
            this,
            discoverViewModel.bannersBean.value ?: arrayListOf(),
            R.layout.jk_item_home_head_banner
        )
        headBannersList.adapter = headBannersAdapter
    }

    private fun updateBannersUI(banners: ArrayList<BannersBean>) {
        if (banners == null) {
            return
        }
        headBannersAdapter.updateData(banners)

    }
}