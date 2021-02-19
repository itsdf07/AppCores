package com.itsdf07.core.app.ui.fragment.home.bean

/**
 * @Description:首页-发现模块中话题区列表的Item Bean对象
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/2/16
 */
data class TopicsBean(
    var name: String,//话题内容
    var icon: String,//icon地址
    var tag: String, //类型，浏览
    var num: String //
)