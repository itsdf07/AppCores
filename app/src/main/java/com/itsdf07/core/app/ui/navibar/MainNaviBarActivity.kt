package com.itsdf07.core.app.ui.navibar

import com.itsdf07.core.app.ui.fragment.home.DiscoverFragment
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.itsdf07.core.app.R
import com.itsdf07.core.app.common.widget.navibar.EasyNavigationBar
import com.itsdf07.core.app.ui.fragment.home.HomeFragment
import com.itsdf07.core.app.ui.fragment.normal.*
import java.util.*

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/2/14
 */
class MainNaviBarActivity : AppCompatActivity() {
    lateinit var navigationBar: EasyNavigationBar
    private val tabText =
        arrayOf("首页", "我的")// "发现", "消息",

    //未选中icon
    private val normalIcon = intArrayOf(R.mipmap.tabbar_main_normal, R.mipmap.tabbar_mine_normal)

    //选中时icon
    private val selectIcon = intArrayOf(R.mipmap.tabbar_main_actived, R.mipmap.tabbar_mine_actived)
    private val fragments: ArrayList<Fragment> = ArrayList()
    private val mHandler = Handler()
    private var flag = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navi_bar)
        navigationBar = findViewById(R.id.navigationBar)
        fragments.add(HomeFragment())
        fragments.add(BFragment())
//        fragments.add(CFragment())
//        fragments.add(DFragment())
//        fragments.add(EFragment())
        navigationBar.titleItems(tabText)
            .normalIconItems(normalIcon)
            .selectIconItems(selectIcon)
            .navigationHeight(50)
            .tabTextSize(10)
            .fragmentList(fragments)
            .centerImageRes(R.mipmap.tabbar_add)
            .centerTextStr("")
//            .anim(null)
            .centerLayoutRule(EasyNavigationBar.RULE_BOTTOM)
            .centerLayoutBottomMargin(0)
            .centerAlignBottom(true)
            .fragmentManager(supportFragmentManager)
            .setOnTabClickListener(object : EasyNavigationBar.OnTabClickListener {
                override fun onTabSelectEvent(view: View?, position: Int): Boolean {
                    return false
                }

                override fun onTabReSelectEvent(view: View?, position: Int): Boolean {
                    return false
                }
            })
            .setOnCenterTabClickListener {
                mHandler.post(Runnable { //＋ 旋转动画
                    if (flag) {
                        navigationBar.centerImage.animate().rotation(45f).duration = 400
                    } else {
                        navigationBar.centerImage.animate().rotation(0f).duration = 400
                    }
                    flag = !flag
                })
                false
            }
            .canScroll(true)
            .mode(EasyNavigationBar.NavigationMode.MODE_ADD)
            .build()
    }
}