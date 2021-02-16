package com.itsdf07.core.app.ui.fragment.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.itsdf07.core.app.data.TabLayoutBean

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/12/30
 */
class TabFragmentAdapter(
    fm: FragmentManager,
    behavior: Int,
    private var tabs: List<TabLayoutBean>
) :
    FragmentStatePagerAdapter(fm, behavior) {

    fun updateData(tabs: List<TabLayoutBean>) {
        this.tabs = tabs
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return tabs[position].fragment;
    }

    override fun getCount(): Int {
        return tabs.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position].tabTitle
    }

}