package com.itsdf07.core.app.data;

import androidx.fragment.app.Fragment;

/**
 * @Description: 用于存放对应TabLayout时的相关数据，如title、View等
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/11/24
 */
public class TabLayoutBean {
    /**
     * 当前Tab对应要展示的页面，Fragment
     */
    private Fragment fragment;
    /**
     * 当前Tab对应要展示的标题，如分类
     */
    private String tabTitle;

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTabTitle() {
        return tabTitle;
    }

    public void setTabTitle(String tabTitle) {
        this.tabTitle = tabTitle;
    }
}
