package com.itsdf07.core.app.ui.activity.dynamic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.itsdf07.core.app.ui.vm.BaseViewModel
import com.itsdf07.core.app.ui.vm.Notify2UILifeDataBean
import com.itsdf07.core.lib.alog.ALog

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/22
 */
class DynamicDetailViewModel : BaseViewModel() {
    private val TAG = "DynamicDetailViewModel"

    /**
     * 动态页面动态详情信息
     */
    private var _dynamicDetailData = MutableLiveData<DynamicBean>().apply {
        value = DynamicBean()
    }
    var dynamicDetailData: LiveData<DynamicBean> = _dynamicDetailData

    /**
     * 动态页面图片轮播图数据
     */
    private var _imagesSlideData = MutableLiveData<ArrayList<ImagesSlideBean>>().apply {
        value = simulateImagesSlideData()
    }
    var imagesSlideData: LiveData<ArrayList<ImagesSlideBean>> = _imagesSlideData

    /**
     * 动态页面图评论数据
     */
    private var _commentData =
        MutableLiveData<ArrayList<DynamicCommentsBean.CommentsBean>>().apply {
            value = arrayListOf()//simulateCommentsData()
        }
    var commentData: LiveData<ArrayList<DynamicCommentsBean.CommentsBean>> = _commentData

    /**
     * 动态页面图评论对应的答复
     */
    private var _commentReplysData =
        MutableLiveData<ArrayList<DynamicCommentReplysBean.ReplysBean>>().apply {
            value = arrayListOf()//simulateCommentReplysData()
        }
    var commentReplysData: LiveData<ArrayList<DynamicCommentReplysBean.ReplysBean>> =
        _commentReplysData

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

    /**
     * 请求动态信息
     * @param themeId 动态对象id
     */
    fun requeryDynamicDetails(themeId: Int) {
        //TODO 根据 themeId 进行网络数据请求后返回 DynamicBean 对象
        var dynameicBean = DynamicBean().apply {
            var theme_imgsData = arrayListOf<DynamicBean.ThemeImgsBean>()
            for (image in simulateImagesSlideData()) {
                theme_imgsData.add(DynamicBean.ThemeImgsBean().apply {
                    t_id = image.id
                    img = image.imgUrl
                })
            }
            author_name = "叫我 ASO"
            author_avatar = "http://static.imjk.club/avatar/avatar_1608447649.jpg"
            theme_content = "针不戳，红蓝格好好看，整体一套很有设计感，图是原相机，取回来立马换上#斩男色口红色卡 哈哈"
            theme_imgs = theme_imgsData
            like_users = simulateLikeUserData()
            like_num = 5
            theme_time = 1615541491
        }
        _dynamicDetailData.value = dynameicBean
    }

    /**
     * @param commentId 话题Id
     * @param requReplysSize 本次加载的评论的数量
     * @param
     */
    fun loadMoreComments(themeId: Int, requCommentsSize: Int) {
        ALog.vTag(TAG, "themeId:$themeId,requCommentsSize:$requCommentsSize")
        var tempCommentsData: ArrayList<DynamicCommentsBean.CommentsBean> =
            if (_commentData.value == null) {
                arrayListOf()
            } else {
                _commentData.value!!
            }
        //currentSize debug使用，用于叠加commentId
        var currentSize: Int = _commentData.value!!.size
        for (i in 1..requCommentsSize) {
            tempCommentsData.add(DynamicCommentsBean.CommentsBean().apply {
                user_id = 21644
                content = "裙子颜色很漂亮，面料很有质感，穿起来很显"
                reply_num = 2
                comment_id = currentSize
                created_time = 1611058861
                user_name = "RL11"
                user_avatar = "http://static.imjk.club/avatar/avatar_1608447649.jpg"
                is_auth = 0
                is_author = 1
                like_num = 0
                is_like = 0

            })
            currentSize += 1
        }

        _commentData.value = tempCommentsData
    }

    /**
     * @param commentId 评论的id
     * @param requReplysSize 本次加载的回复数量
     */
    fun loadMoreReplys(
        commentId: Int,
        requReplysSize: Int
    ) {
        for (commentBean in _commentData.value!!) {
            if (commentBean.comment_id == commentId) {
                for (i in 1..requReplysSize) {
                    commentBean.replys.add(DynamicCommentReplysBean.ReplysBean().apply {
                        user_id = 21644
                        content = "好康好康！没有色差 布料摸起来也很舒服"
                        reply_uid = 0
                        reply_id = 2
                        created_time = 1611058861
                        user_name = "RL11"
                        user_avatar = "http://static.imjk.club/avatar/avatar_1608447649.jpg"
                        is_auth = 0
                        is_author = 1
                        reply_avatar = "http://static.imjk.club/avatar/avatar_1608447649.jpg"
                        reply_name = "叫我ASO"
                        like_num = 0
                        is_like = 0
                    })
                }
                break
            }
        }
        netNotifyLifeData.value = Notify2UILifeDataBean().apply {
            code = 0
            requestUrl = commentId.toString()
            msg = "拉取成功"
        }
    }

    //--------------------------------- 数据模拟
    /**
     * 模拟动态页面素材轮播图
     */
    private fun simulateImagesSlideData(): ArrayList<ImagesSlideBean> {
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
    private fun simulateLikeUserData(): ArrayList<DynamicBean.LikeUsersBean> {
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

    private fun simulateCommentsData(): ArrayList<DynamicCommentsBean.CommentsBean> {
        var commentsBean = arrayListOf<DynamicCommentsBean.CommentsBean>()
        commentsBean.add(DynamicCommentsBean.CommentsBean().apply {
            user_id = 21644
            content = "裙子颜色很漂亮，面料很有质感，穿起来很显"
            reply_num = 2
            comment_id = 1
            created_time = 1611058861
            user_name = "RL11"
            user_avatar = "http://static.imjk.club/avatar/avatar_1608447649.jpg"
            is_auth = 0
            is_author = 1
            like_num = 0
            is_like = 0

        })
        commentsBean.add(DynamicCommentsBean.CommentsBean().apply {
            user_id = 21643
            content = "裙子颜色很漂亮，面料很有质感，穿起来很显"
            reply_num = 0
            comment_id = 1
            created_time = 1611058861
            user_name = "ASO"
            user_avatar = "http://static.imjk.club/avatar/avatar_1608447649.jpg"
            is_auth = 1
            is_author = 0
            like_num = 5
            is_like = 1
        })
        return commentsBean
    }

    private fun simulateCommentReplysData(): ArrayList<DynamicCommentReplysBean.ReplysBean> {
        var commentReplysBean = arrayListOf<DynamicCommentReplysBean.ReplysBean>()
        commentReplysBean.add(DynamicCommentReplysBean.ReplysBean().apply {
            user_id = 21644
            content = "好康好康！没有色差 布料摸起来也很舒服"
            reply_uid = 0
            reply_id = 2
            created_time = 1611058861
            user_name = "RL11"
            user_avatar = "http://static.imjk.club/avatar/avatar_1608447649.jpg"
            is_auth = 0
            is_author = 1
            reply_avatar = "http://static.imjk.club/avatar/avatar_1608447649.jpg"
            reply_name = "叫我ASO"
            like_num = 0
            is_like = 0

        })
        commentReplysBean.add(DynamicCommentReplysBean.ReplysBean().apply {
            user_id = 21643
            content = "11好康好康！没有色差 布料摸起来也很舒服"
            reply_uid = 0
            reply_id = 2
            created_time = 1611058861
            user_name = "RL11"
            user_avatar = "http://static.imjk.club/avatar/avatar_1608447649.jpg"
            is_auth = 0
            is_author = 1
            reply_avatar = "http://static.imjk.club/avatar/avatar_1608447649.jpg"
            reply_name = "叫我ASO"
            like_num = 0
            is_like = 0
        })
        return commentReplysBean
    }

}