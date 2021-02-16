package com.itsdf07.core.app.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.itsdf07.core.app.R
import com.itsdf07.core.app.data.TabLayoutBean
import com.itsdf07.core.app.jk.JKAppCacheCommon
import com.itsdf07.core.lib.alog.ALog

/**
 * @Description: 首页
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/2/15
 */
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var homeViewModel: HomeViewModel

    lateinit var rootView: View

    /**
     * TabLayout布局
     */
    private lateinit var tabLayoutIndicator: TabLayout

    /**
     * ViewPager布局
     */
    private lateinit var tabViewPager: ViewPager

    /**
     * ViewPager adapter
     */
    private lateinit var tabLayoutIndicatorAdapter: TabFragmentAdapter

    /**
     * 签到
     */
    private lateinit var signActivityTheme: ImageView

    /**
     * 消息
     */
    private lateinit var ivMessageCommunity: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
        initAllView()
    }

    private fun initAllView() {
        initTab()

        signActivityTheme = rootView.findViewById(R.id.sign_activity_theme)
        updateSignActivity()
        signActivityTheme.setOnClickListener {
//            val intent = Intent(activity, WebActivity::class.java)
//            intent.putExtra("url", JKAppCacheCommon.SIGN_URL_TARGET_URL_HOMG)
//            activity!!.startActivity(intent)
            Toast.makeText(context, "将会跳转到签到页面", Toast.LENGTH_SHORT).show()
        }

        ivMessageCommunity = rootView.findViewById(R.id.message_community)
        Glide.with(this)
            .load(JKAppCacheCommon.MESSAGE_COMMONITY_URL_BG_IMG)
            .centerCrop()
            .placeholder(R.mipmap.all_ico_message)
            .into(ivMessageCommunity)
        ivMessageCommunity.setOnClickListener {
//            val intent = Intent(activity, PushMessageActivity::class.java)
//            activity!!.startActivity(intent)
            Toast.makeText(context, "将会跳转到消息页面", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 刷新与签到相关的UI
     */
    private fun updateSignActivity() {
        signActivityTheme.visibility =
            if (TextUtils.isEmpty(JKAppCacheCommon.SIGN_URL_BG_IMG_HOMG)) View.GONE else View.VISIBLE
        if (signActivityTheme.visibility == View.VISIBLE) {
            Glide.with(this)
                .load(JKAppCacheCommon.SIGN_URL_BG_IMG_HOMG)
                .placeholder(R.mipmap.main_ico_vip)
                .error(R.mipmap.main_ico_vip)
                .into(signActivityTheme)
        }
    }


    private fun initTab() {
        tabLayoutIndicator = rootView.findViewById(R.id.tab_indicator)
        val tabs: List<TabLayoutBean> = homeViewModel.tabs.value!!

        //TabLayout添加自定义tab Item布局视图
//        for (tabLayoutBean in tabs) {
//            tabLayoutIndicator.addTab(getTabView(tabLayoutBean.tabTitle))
//        }
        tabViewPager = rootView.findViewById(R.id.vp_tab_pager)
        tabLayoutIndicatorAdapter = TabFragmentAdapter(
            childFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            homeViewModel.tabs.value!!
        )
        tabViewPager.adapter = tabLayoutIndicatorAdapter
        tabLayoutIndicator.setupWithViewPager(tabViewPager)

        tabLayoutIndicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {
                ALog.i("onTabReselected->TabLayout二次点击了 ${tab?.text}")
                tab.customView?.findViewById<View>(R.id.tab_indicator_line)?.visibility =
                    View.VISIBLE
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                ALog.i("onTabUnselected->TabLayout的 ${tab?.text} 脱离了选中状态")
                tab.customView?.findViewById<View>(R.id.tab_indicator_line)?.visibility =
                    View.INVISIBLE
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                ALog.i("onTabSelected->TabLayout选中了 ${tab?.text}")
                tab.customView?.findViewById<View>(R.id.tab_indicator_line)?.visibility =
                    View.VISIBLE
            }

        })

        tabViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                ALog.i("position:${position}")
//                tabViewPager.currentItem = position
//                selectIndex = position
            }

            /**
             * @param state 0-静止状态，1-滑动中，2-滑动结束
             */
            override fun onPageScrollStateChanged(state: Int) {
                ALog.i("state:${state}")
            }
        })
        for (index in tabs.indices) {
            resetTabViews(index, tabs[index].tabTitle)
        }
        tabViewPager.currentItem = 0

    }

    private fun getTabView(title: String): TabLayout.Tab {
        var tab: TabLayout.Tab = tabLayoutIndicator.newTab()
        val tabItem = LayoutInflater.from(context).inflate(R.layout.item_tab_home, null)
        val txtTitle: TextView = tabItem.findViewById(R.id.title_name)
        txtTitle.text = title
        tab.customView = tabItem
        return tab
    }

    private fun resetTabViews(position: Int, title: String) {
        val view = LayoutInflater.from(context).inflate(R.layout.item_tab_home, null)
        val txtTitle: TextView = view.findViewById(R.id.title_name)
        txtTitle.text = title
        var tabLayout: TabLayout.Tab = tabLayoutIndicator.getTabAt(position)!!
        tabLayout.customView = view
        if (position == 0) {
            tabLayout.select()
        }
    }

}