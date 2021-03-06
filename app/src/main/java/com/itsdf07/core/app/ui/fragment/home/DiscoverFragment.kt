package com.itsdf07.core.app.ui.fragment.home

import DiscoverActivitysAdapter
import DiscoverBannersAdapter
import DiscoverEggsAdapter
import DiscoverBlocksAdapter
import DiscoverTopicsAdapter
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.itsdf07.core.app.R
import com.itsdf07.core.app.common.UniversalItemDecoration
import com.itsdf07.core.app.common.utils.DeviceUtils
import com.itsdf07.core.app.common.widget.SlideViewPager
import com.itsdf07.core.app.jk.JKUrl
import com.itsdf07.core.app.ui.fragment.home.bean.*
import com.itsdf07.core.lib.alog.ALog
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

    /**
     * 头部大图区
     */
    private lateinit var headActivitysList: RecyclerView
    private lateinit var headActivitysAdapter: DiscoverActivitysAdapter

    /**
     * 轮播图区域
     */
    private lateinit var layoutTurns: RelativeLayout
    private lateinit var turnsViewPager: SlideViewPager
    private lateinit var turnsAdapter: DiscoverTurnsAdapter
    private lateinit var turnsIndicator: LinearLayout

    /**
     * 头部方块区
     */
    private lateinit var headBlocksLis: RecyclerView
    private lateinit var headBlocksAdapter: DiscoverBlocksAdapter

    /**
     * 热门话题
     */
    lateinit var headTopicTip: TextView
    private lateinit var headTopicsList: RecyclerView
    private lateinit var headTopicsAdapter: DiscoverTopicsAdapter

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
        discoverViewModel.eggsBean.observe(requireActivity(), Observer {
            updateEggsUI(it)
        })
        discoverViewModel.activitysBean.observe(requireActivity(), Observer {
            updateActivitysUI(it)
        })

        discoverViewModel.turnsBean.observe(requireActivity(), Observer {
            updateTurnsUI(it)
        })
        discoverViewModel.blocksBean.observe(requireActivity(), Observer {
            updateBlocksUI(it)
        })
        discoverViewModel.topicsBean.observe(requireActivity(), Observer {
            updateTopicsUI(it)
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
                    decoration.top = 0
                } else {
                    decoration.top = DeviceUtils.dp2px(requireContext(), 20f)
                }
                if (position % 2 == 0) {//第一行的第一列，通用的话可以使用position % 2 == 0
                    decoration.left = DeviceUtils.dp2px(requireContext(), 15f)
                    decoration.right = DeviceUtils.dp2px(requireContext(), 5.5f)
                } else {//第一行的第二列，通用的话可以使用position % 2 == 1
                    decoration.left = DeviceUtils.dp2px(requireContext(), 5.5f)
                    decoration.right = DeviceUtils.dp2px(requireContext(), 15f)
                }
                decoration.bottom = 0
                return decoration
            }
        })

        headBannersList.layoutManager =
            GridLayoutManager(requireContext(), 2)
        headBannersAdapter = DiscoverBannersAdapter(
            this,
            discoverViewModel.bannersBean.value ?: arrayListOf(),
            R.layout.jk_item_home_head_banner
        )
        headBannersList.adapter = headBannersAdapter

        //蛋蛋区
        var eggs = discoverViewModel.eggsBean.value ?: arrayListOf()
        headEggsList = rootView.findViewById(R.id.eggs_list)
        headEggsList.layoutManager =
            GridLayoutManager(requireContext(), 1)
        headEggsAdapter = DiscoverEggsAdapter(
            this,
            eggs,
            R.layout.jk_item_home_head_egg
        )
        headEggsList.adapter = headEggsAdapter

        //大图区
        headActivitysList = rootView.findViewById(R.id.activitys_list)
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        headActivitysList.addItemDecoration(object : UniversalItemDecoration() {
            override fun getItemOffsets(position: Int): Decoration? {
                val decoration = ColorDecoration()
                decoration.decorationColor = Color.TRANSPARENT
                if (position == 0) {
                    decoration.top = 0
                } else {
                    decoration.top = DeviceUtils.dp2px(requireContext(), 20f)
                }
                decoration.bottom = 0
                decoration.left = DeviceUtils.dp2px(requireContext(), 15f)
                decoration.right = DeviceUtils.dp2px(requireContext(), 15f)
                return decoration
            }
        })
        headActivitysList.layoutManager = layoutManager
        headActivitysAdapter = DiscoverActivitysAdapter(
            this,
            discoverViewModel.activitysBean.value ?: arrayListOf(),
            R.layout.jk_item_home_head_activity
        )
        headActivitysList.adapter = headActivitysAdapter
        //轮播区
        layoutTurns = rootView.findViewById(R.id.layout_slides)
        turnsViewPager = rootView.findViewById(R.id.slides_list)
        turnsIndicator = rootView.findViewById(R.id.slides_indicator)

        turnsViewPager.setOnViewPagerTouchListen {
            ALog.v("setOnViewPagerTouchListen:$it")
        }

        turnsAdapter = DiscoverTurnsAdapter(this)
        turnsViewPager.adapter = turnsAdapter
        turnsAdapter.setData(discoverViewModel.turnsBean.value ?: arrayListOf())
        turnsViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                var realPosition = 0
                if (turnsAdapter.dataRealSize !== 0) {
                    realPosition = position % turnsAdapter!!.dataRealSize
                }
                selectedPoint(realPosition)
            }

        })
        turnsViewPager.setCurrentItem(0, false)

        //方块区
        headBlocksLis = rootView.findViewById(R.id.blocks_list)
        headBlocksLis.addItemDecoration(object : UniversalItemDecoration() {
            override fun getItemOffsets(position: Int): Decoration? {
                val decoration = ColorDecoration()
                decoration.decorationColor = Color.TRANSPARENT;
                decoration.top = 0
                decoration.bottom = 0
                decoration.right = DeviceUtils.dp2px(requireContext(), 15f)
                if (position == 0) {
                    decoration.left = DeviceUtils.dp2px(requireContext(), 15f)
                } else {
                    decoration.left = 0
                }
                return decoration
            }
        })

        headBlocksLis.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        headBlocksAdapter = DiscoverBlocksAdapter(
            this,
            discoverViewModel.blocksBean.value ?: arrayListOf(),
            R.layout.jk_item_home_head_block
        )
        headBlocksLis.adapter = headBlocksAdapter

        //话题区
        headTopicTip = rootView.findViewById(R.id.topic_title_tip)
        headTopicsList = rootView.findViewById(R.id.hots_list)
        headTopicsList.addItemDecoration(object : UniversalItemDecoration() {
            override fun getItemOffsets(position: Int): Decoration? {
                val decoration = ColorDecoration()
                decoration.decorationColor = Color.TRANSPARENT
                if (position < 2) {
                    decoration.top = 0
                } else {
                    decoration.top = DeviceUtils.dp2px(requireContext(), 15f)
                }

                decoration.bottom = 0
                if (position % 2 == 0) {//第一行的第一列，通用的话可以使用position % 2 == 0
                    decoration.left = 0
                    decoration.right = DeviceUtils.dp2px(requireContext(), 5.5f)
                } else {//第一行的第二列，通用的话可以使用position % 2 == 1
                    decoration.left = DeviceUtils.dp2px(requireContext(), 5.5f)
                    decoration.right = 0
                }
                return decoration
            }
        })

        headTopicsList.layoutManager =
            GridLayoutManager(requireContext(), 2)
        headTopicsAdapter = DiscoverTopicsAdapter(
            this,
            discoverViewModel.topicsBean.value ?: arrayListOf(),
            R.layout.jk_item_home_head_topic
        )
        headTopicsList.adapter = headTopicsAdapter

    }

    private fun updateBannersUI(banners: ArrayList<BannersBean>) {
        if (banners == null || banners.size == 0) {
            headBannersList.visibility = View.GONE
            return
        }
        headBannersList.visibility = View.VISIBLE
        headBannersAdapter.updateData(banners)
    }

    private fun updateEggsUI(eggs: ArrayList<EggsBean>) {
        if (eggs == null || eggs.size == 0) {
            headEggsList.visibility = View.GONE
            return
        }
        headEggsList.visibility = View.VISIBLE
        if (headEggsList.itemDecorationCount > 0) {//headEggsAdapter为重新实例出来的，必须先清空之前的分割线，否则会不断的叠加间距
            for (i in 0 until headEggsList.itemDecorationCount) {
                headEggsList.removeItemDecorationAt(i)
            }
        }
        headEggsList.addItemDecoration(object : UniversalItemDecoration() {
            override fun getItemOffsets(position: Int): Decoration? {
                val decoration = ColorDecoration()
                decoration.decorationColor = Color.TRANSPARENT;
                decoration.top = 0
                decoration.bottom = 0
                if (position == 0) {//列表的索引1和2，目的是绘制列表顶部分割线高度
                    decoration.left = DeviceUtils.dp2px(requireContext(), 5f)
                    decoration.right = DeviceUtils.dp2px(requireContext(), 5.5f)
                } else if (position == eggs.size - 1) {
                    decoration.left = DeviceUtils.dp2px(requireContext(), 5.5f)
                    decoration.right = DeviceUtils.dp2px(requireContext(), 5f)
                } else {
                    decoration.left = DeviceUtils.dp2px(requireContext(), 5.5f)
                    decoration.right = DeviceUtils.dp2px(requireContext(), 5.5f)
                }

                return decoration
            }
        })
        headEggsList.layoutManager =
            GridLayoutManager(requireContext(), eggs.size)
        headEggsAdapter = DiscoverEggsAdapter(
            this,
            eggs,
            R.layout.jk_item_home_head_egg
        )
        headEggsList.adapter = headEggsAdapter
    }

    private fun updateActivitysUI(activitys: ArrayList<ActivitysBean>) {
        if (activitys == null || activitys.size == 0) {
            headActivitysList.visibility = View.GONE
            return
        }
        headActivitysList.visibility = View.VISIBLE
        headActivitysAdapter.updateData(activitys)
    }

    private fun updateTurnsUI(turns: ArrayList<TurnsBean>) {
        if (turns == null || turns.size == 0) {
            layoutTurns.visibility = View.GONE
            return
        }
        layoutTurns.visibility = View.VISIBLE
        initTurnsIndicator(turns)
        turnsAdapter.setData(turns)
    }

    private fun updateBlocksUI(blocks: ArrayList<BlocksBean>) {
        if (blocks == null || blocks.size == 0) {
            headBlocksLis.visibility = View.GONE
            return
        }
        headBlocksLis.visibility = View.VISIBLE
        headBlocksAdapter.updateData(blocks)
    }

    private fun updateTopicsUI(topics: ArrayList<TopicsBean>) {
        if (topics == null || topics.size == 0) {
            headTopicTip.visibility = View.GONE
            headTopicsList.visibility = View.GONE
            return
        }
        if (TextUtils.isEmpty(discoverViewModel.topicsTip.value)) {
            headTopicTip.visibility = View.GONE
        } else {
            headTopicTip.visibility = View.VISIBLE
        }
        headTopicsList.visibility = View.VISIBLE
        headTopicsAdapter.updateData(topics)
    }


    /**
     * 轮播区页码指示器状态UI
     */
    private fun selectedPoint(realPosition: Int) {
        for (i in 0 until turnsIndicator.childCount) {
            val point: View = turnsIndicator.getChildAt(i)
            if (i == realPosition) {
                point.setBackgroundResource(R.drawable.shape_point_selected)
            } else {
                point.setBackgroundResource(R.drawable.shape_point_normal)
            }
        }
    }

    /**
     * 初始轮播区页码指示器UI
     */
    private fun initTurnsIndicator(turns: ArrayList<TurnsBean>) {
        if (turns == null) {
            return
        }
        turnsIndicator.removeAllViews()
        for (i in 0 until turns.size) {
            val point = View(context)
            if (i == 0) {
                point.setBackgroundResource(R.drawable.shape_point_selected)
            } else {
                point.setBackgroundResource(R.drawable.shape_point_normal)
            }
            val params = LinearLayout.LayoutParams(
                DeviceUtils.dp2px(context, 6f),
                DeviceUtils.dp2px(context, 6f)
            )
            params.leftMargin = DeviceUtils.dp2px(context, 6f)
            point.layoutParams = params
            turnsIndicator.addView(point)
        }
    }

    private val onRefreshListener = OnRefreshListener {
        refreshLayout.finishRefresh(5000)
        discoverViewModel.requeryDiscover()
    }
}