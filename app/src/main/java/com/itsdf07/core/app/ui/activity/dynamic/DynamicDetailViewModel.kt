package com.itsdf07.core.app.ui.activity.dynamic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.itsdf07.core.app.ui.vm.BaseViewModel

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/22
 */
class DynamicDetailViewModel : BaseViewModel() {
    /**
     * 动态页面图片轮播图数据
     */
    private var _dynamicDetailData = MutableLiveData<DynamicBean>().apply {

        value = DynamicBean().apply {
            var theme_imgsData = arrayListOf<DynamicBean.ThemeImgsBean>()
            for (image in simulateImagesSlideData()) {
                theme_imgsData.add(DynamicBean.ThemeImgsBean().apply {
                    t_id = image.id
                    img = image.imgUrl
                })
            }
            author_name = "叫我 ASO"
            theme_content = "针不戳，红蓝格好好看，整体一套很有设计感，图是原相机，取回来立马换上#斩男色口红色卡 哈哈"
            theme_imgs = theme_imgsData
            like_users = simulateLikeUserData()
            like_num = 5
            theme_time = 1615541491
        }
    }
    var dynamicDetailData: LiveData<DynamicBean> = _dynamicDetailData

    /**
     * 动态页面图片轮播图数据
     */
    private var _imagesSlideData = MutableLiveData<ArrayList<ImagesSlideBean>>().apply {
        value = simulateImagesSlideData()
    }
    var imagesSlideData: LiveData<ArrayList<ImagesSlideBean>> = _imagesSlideData

    fun themeImgs2ImagesSlideBean(themeImgsData: ArrayList<DynamicBean.ThemeImgsBean>): ArrayList<ImagesSlideBean> {
        var imagesSlideDatas: ArrayList<ImagesSlideBean> = arrayListOf()
        for (themeImageBean in themeImgsData) {
            imagesSlideDatas.add(ImagesSlideBean().apply {
                id = themeImageBean.t_id
                imgUrl = themeImageBean.img
            })
        }
        return imagesSlideDatas
    }

    //--------------------------------- 数据模拟
    /**
     * 模拟动态页面素材轮播图
     */
    fun simulateImagesSlideData(): ArrayList<ImagesSlideBean> {
        var imagesSlideList = arrayListOf<ImagesSlideBean>()
        imagesSlideList.add(ImagesSlideBean().apply {
            id = 3
            imgUrl = "https://static.imjk.club/template/1609927938986.png?1035_1035"
        })
        imagesSlideList.add(ImagesSlideBean().apply {
            id = 0
            imgUrl = "https://static.imjk.club/template/1609927938986.png?1035_1035"
        })
        return imagesSlideList
    }

    /**
     * 模拟动态页面点赞用户数据
     */
    fun simulateLikeUserData(): ArrayList<DynamicBean.LikeUsersBean> {
        var likeUsersBean = arrayListOf<DynamicBean.LikeUsersBean>()
        likeUsersBean.add(DynamicBean.LikeUsersBean().apply {
            name = "111"
            avatar = "http://thirdqq.qlogo.cn/g?b=oidb&k=pgHzDqgGZwFxf2iaptq2xYQ&s=100&t=1590772613"
        })
        likeUsersBean.add(DynamicBean.LikeUsersBean().apply {
            name = "RL11"
            avatar = "http://static.imjk.club/avatar/avatar_1608447649.jpg"
        })
        return likeUsersBean
    }


}