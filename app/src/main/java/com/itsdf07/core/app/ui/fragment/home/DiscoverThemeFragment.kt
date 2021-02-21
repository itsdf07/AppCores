package com.itsdf07.core.app.ui.fragment.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itsdf07.core.app.R

/**
 * @Description: 动态列表
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/2/19
 */
class DiscoverThemeFragment : Fragment() {
    private var themeTagId = 1   // 备注： -1 我的页面动态  -2 我的页面喜欢   by dxw

    companion object {
        fun newInstance() = DiscoverThemeFragment()
    }

    /**
     * @param tId 动态分类标签ID
     * @param isLoadData 是否需要马上加载数据
     */
    fun setThemeId(tId: Int, isLoadData: Boolean) {
        themeTagId = tId
        if (isLoadData) {
            //TODO 拉取数据
        }
    }

    private lateinit var viewModel: DiscoverThemeViewModel
    private lateinit var rootView: View

    private lateinit var themeList: RecyclerView

    /**
     * 数动态数据的视图
     */
    private lateinit var noThemeData: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_discover_theme, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DiscoverThemeViewModel::class.java)
        // TODO: Use the ViewModel

        initAllView()
    }

    private fun initAllView() {
        themeList = rootView.findViewById(R.id.rv_product_list)
        noThemeData = rootView.findViewById(R.id.discover_no_data)
    }

}