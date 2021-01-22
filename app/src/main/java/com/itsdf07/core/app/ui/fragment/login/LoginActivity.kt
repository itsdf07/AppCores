package com.itsdf07.core.app.ui.fragment.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import com.gyf.immersionbar.ktx.immersionBar
import com.itsdf07.core.app.R
import com.itsdf07.core.app.common.BaseViewPager
import com.itsdf07.core.app.data.TabLayoutBean
import com.itsdf07.core.app.databinding.ActivityLoginBinding
import com.itsdf07.core.app.view.TitleBar
import com.itsdf07.core.lib.alog.ALog


/**
 * @Description: 用户登录/注册页面
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/12/23
 */
class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var dataBinding: ActivityLoginBinding

    /**
     * 页面的标题栏
     */
    private lateinit var titleBar: TitleBar

    /**
     * TabLayout布局
     */
    private lateinit var tabLayoutIndicator: TabLayout

    /**
     * ViewPager布局
     */
    private lateinit var tabViewPager: BaseViewPager

    /**
     * ViewPager adapter
     */
    private lateinit var tabLayoutIndicatorAdapter: TabFragmentAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        immersionBar {
            statusBarColor(R.color.transparent)
            navigationBarColor(R.color.purple_200)
        }
        loginViewModel =
            ViewModelProvider(this).get(LoginViewModel::class.java)
        dataBinding.loginVM = loginViewModel
        dataBinding.lifecycleOwner = this
        titleBar = dataBinding.titlebar
        titleBar.apply {
            setOnBackClickListener {
                finish()
            }
            setOnShareClickListener {
                Toast.makeText(
                    applicationContext, "分享功能尚未实现", Toast.LENGTH_LONG
                ).show()
            }
        }
        initTab()
        Glide.with(this)
            .load(R.mipmap.icon_logo)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .centerCrop()
            .placeholder(R.mipmap.icon_logo)
            .into(dataBinding.header)
    }

    /**
     * 初始化Tab相关事宜
     * 1、
     */
    private fun initTab() {
        //对应 TabLayout 的 Lifedata 数据
        val tabs: MutableLiveData<ArrayList<TabLayoutBean>> = loginViewModel.loadFragments()

        tabLayoutIndicator = dataBinding.tabIndicator


        tabLayoutIndicatorAdapter = TabFragmentAdapter(
            supportFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            tabs.value!!
        )
        tabViewPager = dataBinding.vpTabPager
        tabViewPager.adapter = tabLayoutIndicatorAdapter
        tabLayoutIndicator.setupWithViewPager(tabViewPager)
        tabViewPager.currentItem = 0
//        //初始化TabLayout数据时先清空一下数据（非必须，根据业务需求）
//        tabLayoutIndicator.removeAllTabs()
//        for (tabBean in tabs.value!!) {
//            val tab = tabLayoutIndicator.newTab().setCustomView(R.layout.tab_item_layout)
//            var tabTitle = tab.customView!!.findViewById<TextView>(R.id.tab_item_title)
//            tabTitle.text = tabBean.tabTitle
//            tabLayoutIndicator.addTab(tab)
//        }
        tabLayoutIndicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                ALog.i("onTabReselected->TabLayout二次点击了 ${tab?.text}")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                ALog.i("onTabUnselected->TabLayout的 ${tab?.text} 脱离了选中状态")
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                ALog.i("onTabSelected->TabLayout选中了 ${tab?.text}")
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
            }

            /**
             * @param state 0-静止状态，1-滑动中，2-滑动结束
             */
            override fun onPageScrollStateChanged(state: Int) {
                ALog.i("state:${state}")
            }
        })


    }

}

