package com.itsdf07.core.app.ui.fragment.home.bean

/**
 * @Description:首页-发现模块中方块区列表的Item Bean对象
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/2/16
 */
data class BlocksBean(
    var id: Int,
    var title: String,//蛋蛋分类名称，如 样机
    var img: String,//素材地址
    var url: String,//跳转目标地址
    var isLogin: Int //是否需要登录 0-否 1-是
)