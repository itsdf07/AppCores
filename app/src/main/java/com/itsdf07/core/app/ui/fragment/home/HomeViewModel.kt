package com.itsdf07.core.app.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itsdf07.core.app.data.TabLayoutBean
import com.itsdf07.core.app.ui.fragment.normal.AFragment
import com.itsdf07.core.app.ui.fragment.normal.BFragment

class HomeViewModel : ViewModel() {
    /**
     * ViewPager中对应的页面集合
     */
    private val _tabs = MutableLiveData<ArrayList<TabLayoutBean>>().apply {
        value = loadTabs()
    }
    val tabs: LiveData<ArrayList<TabLayoutBean>> = _tabs

    /**
     * ViewPager中对应的页面集合
     */
    private fun loadTabs(): ArrayList<TabLayoutBean> {
        var tabLayoutBean: ArrayList<TabLayoutBean> = arrayListOf()
        tabLayoutBean.add(TabLayoutBean().apply {
            tabTitle = "发现"
            fragment = AFragment()
        })
        tabLayoutBean.add(TabLayoutBean().apply {
            tabTitle = "关注"
            fragment = BFragment()
        })

        return tabLayoutBean
    }
}