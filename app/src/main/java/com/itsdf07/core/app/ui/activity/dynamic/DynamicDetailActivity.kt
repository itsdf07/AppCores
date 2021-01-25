package com.itsdf07.core.app.ui.activity.dynamic

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.itsdf07.core.app.R
import com.itsdf07.core.app.common.utils.DateTimeUtils
import com.itsdf07.core.app.common.utils.DeviceUtils
import com.itsdf07.core.app.common.widget.SlideViewPager
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * @Description:社区动态详情页面
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/22
 */
class DynamicDetailActivity : AppCompatActivity(), View.OnClickListener {
    /**
     * 动态者的头像
     */
    private lateinit var titleUserAvatar: ImageView

    /**
     * 动态者的昵称
     */
    private lateinit var titleUserDispalyName: TextView

    /**
     * 动态内容的日期
     */
    private lateinit var titleDynamicDate: TextView

    /**
     * 动态页面的关注按钮
     */
    private lateinit var titleFollowBtn: TextView

    /**
     * 动态内容
     */
    private lateinit var dynamicContent: TextView

    /**
     * 图片轮播器
     */
    private lateinit var imagesSlideViewPager: SlideViewPager

    /**
     * 图片轮播页面提示器_页码
     */
    private lateinit var imagesSlideIndicatorPage: TextView

    /**
     * 图片轮播页面提示器_红点
     */
    private lateinit var imagesSlideIndicatorPoint: LinearLayout

    /**
     * 图片轮播页面提示内容
     */
    private lateinit var imagesSlideContentTip: TextView

    /**
     * 点赞用户1头像
     */
    private lateinit var likeUserAvatar1: ImageView

    /**
     * 点赞用户2头像
     */
    private lateinit var likeUserAvatar2: ImageView

    /**
     * 点赞用户内容相关提示
     */
    private lateinit var likeUserTip: TextView

    private lateinit var dynamicDetailsViewModel: DynamicDetailViewModel
    private lateinit var dynamicDetailsSlideAdapter: ImagesSlideAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_detail)
        dynamicDetailsViewModel = ViewModelProvider(this).get(DynamicDetailViewModel::class.java)
        dynamicDetailsViewModel.dynamicDetailData.observe(this, Observer {
            dynamicDetailsSlideAdapter.setData(dynamicDetailsViewModel.themeImgs2ImagesSlideBean(it.theme_imgs as ArrayList<DynamicBean.ThemeImgsBean>))
            initThemeImagesSlideIndicator()
            initDynamicContentStyle(it.theme_content)
            initLikeUserStatus()
            initTitle()
        })
        dynamicDetailsViewModel.imagesSlideData.observe(this, Observer {
            dynamicDetailsSlideAdapter.setData(it)
        })
        dynamicDetailsViewModel.netNotifyLifeData.observe(this, Observer {
            Toast.makeText(this, it.msg, Toast.LENGTH_SHORT).show()
        })
        initView()
    }

    private fun initView() {
        var titleBack: ImageView = findViewById(R.id.title_back)
        titleBack.setOnClickListener { finish() }
        titleUserAvatar = findViewById(R.id.title_avatar)
        titleUserAvatar.setOnClickListener(this)
        titleUserDispalyName = findViewById(R.id.title_user_display)
        titleDynamicDate = findViewById(R.id.title_user_dynamic_date)
        titleFollowBtn = findViewById(R.id.title_dynamic_follow)
        titleFollowBtn.setOnClickListener(this)
        dynamicContent = findViewById(R.id.dynamic_detail)
        imagesSlideContentTip = findViewById(R.id.image_slide_tip)
        initDynamicContentStyle("针不戳，红蓝格好好看，整体一套很有设计感，图是原相机，取回来立马换上#斩男色口红色卡 asdfasdfasdfasdfsdf")
        initViewPager()
        likeUserAvatar1 = findViewById(R.id.like_user_avatar_1)
        likeUserAvatar2 = findViewById(R.id.like_user_avatar_2)
        likeUserTip = findViewById(R.id.like_user_tip)
        initLikeUserStatus()
        initTitle()
    }

    private fun initTitle() {
        var datas: DynamicBean? = dynamicDetailsViewModel.dynamicDetailData.value
        datas?.let {
            Glide.with(this)
                .load(datas.author_avatar)
                .apply(
                    RequestOptions.circleCropTransform()
                        //通过缓存键检查是否更新
                        .placeholder(R.mipmap.icon_logo)
                        .error(R.mipmap.icon_logo)
                )
                .into(titleUserAvatar)
            titleUserDispalyName.text = datas.author_name
            titleDynamicDate.text = DateTimeUtils.getDateToString(datas.theme_time, 14)
            if (datas.author_relation == 1 || datas.author_relation == 3) {
                titleFollowBtn.visibility = View.GONE
            } else {
                titleFollowBtn.visibility = View.VISIBLE
                when (datas.author_relation) {
                    0 -> {
                        titleFollowBtn.text = "关注"
                    }
                    2 -> {
                        titleFollowBtn.text = "回关"
                    }
                    else -> {
                        titleFollowBtn.visibility = View.GONE
                    }
                }
            }
        }
    }

    /**
     * 点赞用户部分的初始化
     */
    private fun initLikeUserStatus() {
        var datas: DynamicBean? = dynamicDetailsViewModel.dynamicDetailData.value
        var likeCount = datas?.like_num ?: 0
        when (likeCount) {
            0 -> {
                likeUserAvatar1.visibility = View.GONE
                likeUserAvatar2.visibility = View.GONE
            }
            1 -> {
                likeUserAvatar1.visibility = View.VISIBLE
                likeUserAvatar2.visibility = View.GONE
                likeUserTip.text = "${datas!!.like_users[0].name} 点赞了"
                Glide.with(this)
                    .load(datas.like_users[0].avatar)
                    .apply(
                        RequestOptions.circleCropTransform()
                            //通过缓存键检查是否更新
                            .placeholder(R.mipmap.icon_logo)
                            .error(R.mipmap.icon_logo)
                    )
                    .into(likeUserAvatar1)
            }
            else -> {
                likeUserAvatar1.visibility = View.VISIBLE
                likeUserAvatar2.visibility = View.VISIBLE
                likeUserTip.text =
                    "${datas!!.like_users[0].name}、${datas!!.like_users[1].name} 等${likeCount}位朋友点赞了"
                Glide.with(this)
                    .load(datas.like_users[0].avatar)
                    .apply(
                        RequestOptions.circleCropTransform()
                            //通过缓存键检查是否更新
                            .placeholder(R.mipmap.icon_logo)
                            .error(R.mipmap.icon_logo)
                    )
                    .into(likeUserAvatar1)
                Glide.with(this)
                    .load(datas.like_users[1].avatar)
                    .apply(
                        RequestOptions.circleCropTransform()
                            //通过缓存键检查是否更新
                            .placeholder(R.mipmap.icon_logo)
                            .error(R.mipmap.icon_logo)
                    )
                    .into(likeUserAvatar2)
            }
        }
    }

    private fun initViewPager() {
        imagesSlideIndicatorPoint = findViewById(R.id.dynamic_image_slide_indicator_point)
        imagesSlideViewPager = findViewById(R.id.layout_image_slide_viewpager)
        imagesSlideIndicatorPage = findViewById(R.id.image_slide_indicator_page)
        dynamicDetailsSlideAdapter = ImagesSlideAdapter()
//        dynamicDetailsSlideAdapter.setData(dynamicDetailsViewModel.dynamicDetailsData.value!!.theme_imgs)
        imagesSlideViewPager.adapter = dynamicDetailsSlideAdapter
        imagesSlideViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                var realPosition = 0
                if (dynamicDetailsSlideAdapter.dataRealSize !== 0) {
                    realPosition = position % dynamicDetailsSlideAdapter.dataRealSize
                }
                var tid =
                    dynamicDetailsViewModel.dynamicDetailData.value?.theme_imgs?.get(position)?.t_id
                        ?: 0
                if (tid > 0) {
                    imagesSlideContentTip.visibility = View.VISIBLE
                } else {
                    imagesSlideContentTip.visibility = View.GONE
                }
                selectedPoint(realPosition)
            }

        })
        imagesSlideViewPager.setCurrentItem(0, false)
        initThemeImagesSlideIndicator()
    }

    private fun selectedPoint(realPosition: Int) {
        imagesSlideIndicatorPage.text =
            "${realPosition + 1}/${dynamicDetailsSlideAdapter.dataRealSize}"
        for (i in 0 until imagesSlideIndicatorPoint.childCount) {
            val point: View = imagesSlideIndicatorPoint.getChildAt(i)
            if (i == realPosition) {
                point.setBackgroundResource(R.drawable.shape_point_selected)
            } else {
                point.setBackgroundResource(R.drawable.shape_dynamic_detail_point_normal)
            }
        }
    }

    private fun initThemeImagesSlideIndicator() {
        imagesSlideIndicatorPage.text = "${0 + 1}/${dynamicDetailsSlideAdapter.dataRealSize}"
        imagesSlideIndicatorPoint.removeAllViews()
        for (i in 0 until dynamicDetailsSlideAdapter.dataRealSize) {
            val point = View(this)
            if (i == 0) {
                point.setBackgroundResource(R.drawable.shape_point_selected)
            } else {
                point.setBackgroundResource(R.drawable.shape_dynamic_detail_point_normal)
            }
            val params =
                LinearLayout.LayoutParams(DeviceUtils.dp2px(this, 6f), DeviceUtils.dp2px(this, 6f))
            params.leftMargin = DeviceUtils.dp2px(this, 6f)
            point.layoutParams = params
            imagesSlideIndicatorPoint.addView(point)
        }
    }

    private fun initDynamicContentStyle(dynamicContent: String) {
        val spannableStringBuilder = SpannableStringBuilder()
        spannableStringBuilder.append(dynamicContent)
        val startIndex = dynamicContent.indexOf("#")
        var endIndex: Int = startIndex + 1
        val p: Pattern = Pattern.compile(" ")
        val m: Matcher = p.matcher(dynamicContent)
        while (m.find()) {
            if (m.start() > startIndex) {
                endIndex = m.start()
                break
            }
        }

        //设置协议点击事件
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@DynamicDetailActivity, "点击了", Toast.LENGTH_SHORT).show();
            }

            override fun updateDrawState(ds: TextPaint) {
                //super.updateDrawState(ds)
                /**** 这里是对你指定的第6-10个字的处理： ****/
                //设置下划线,默认是有下划线的，false 是去掉下划线
                ds.isUnderlineText = false;

                //设置颜色，默认是蓝色,我这里设置成红色了
                ds.color = Color.parseColor("#349FF2")

//                //设置字体大小
//                ds.textSize = 50f;
                //setFlags 设置：
                //设置删除线
//                ds.setFlags(TextPaint.STRIKE_THRU_TEXT_FLAG);
                //设置粗体
//                ds.setFlags(TextPaint.FAKE_BOLD_TEXT_FLAG);

//                //设置粗体，也可以这样
//                ds.isFakeBoldText = true;
            }
        }
        spannableStringBuilder.setSpan(
            clickableSpan,
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )

        //点击默认会有颜色出现，去掉点击后的颜色：
        //还可以直接写布局中，这样：android:textColorHighlight="#ffffff"
//        tvDynamicContent.setHighlightColor(Color.parseColor("#ffffff"));
        this.dynamicContent.text = spannableStringBuilder
        //设置光标如何移动计量的方法。这句不加的话，点击事件不生效
        this.dynamicContent.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.title_avatar, R.id.layout_title_uer_info -> {
                Toast.makeText(this, "点击跳转到个人主页", Toast.LENGTH_SHORT).show()
            }
            R.id.title_dynamic_follow -> {
                Toast.makeText(this, "点击跳转到关注按钮", Toast.LENGTH_SHORT).show()
//                dynamicDetailsViewModel.requestFollow(
//                    dynamicDetailsViewModel.dynamicDetailsData.value!!.author_id,
//                    1
//                )
            }
        }
    }
}