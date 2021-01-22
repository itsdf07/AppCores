package com.itsdf07.core.app.ui.fragment.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.itsdf07.core.app.data.TabLayoutBean

/**
 * @Description: tabLayout 的
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/11/24
 */
class TabFragmentAdapter(
    fm: FragmentManager,
    behavior: Int,
    private var fargments: List<TabLayoutBean>
) :
    FragmentStatePagerAdapter(fm, behavior) {

    override fun getItem(position: Int): Fragment {
        return fargments[position].fragment;
    }

    override fun getCount(): Int {
        return fargments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fargments[position].tabTitle
    }
}