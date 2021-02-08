package com.itsdf07.core.app.ui.activity.showimg

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.itsdf07.core.app.R

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/2/5
 */
class ShowBigImgActivity : AppCompatActivity() {
    companion object {
        /**
         * 查看大图的数据传输使用的KEY
         */
        const val INTENT_KEY_IMGS = "SHOW_IMGS"

        /**
         * 展示对应索引位置的图片
         */
        const val INTENT_KEY_POSITION = "SHOW_IMG_POSITION"
    }

    private lateinit var imgList: List<String>
    private var imgPosition: Int = 0
    private var currentPosition: Int = 0

    private lateinit var bigImgList: RecyclerView
    private lateinit var bigImgAdapter: BigImgAdapter

    private lateinit var snapHelper: PagerSnapHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_big_img)
        imgList = intent.getStringArrayListExtra(INTENT_KEY_IMGS) ?: simulation()
        imgPosition = intent.getIntExtra(INTENT_KEY_POSITION, 0)
        if (imgPosition > (imgList.size - 1)) {
            imgPosition = imgList.size - 1
        }
        currentPosition = imgPosition
        // 延迟共享动画的执行
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition()
        }

        snapHelper = object : PagerSnapHelper() {
            override fun findTargetSnapPosition(
                layoutManager: RecyclerView.LayoutManager?,
                velocityX: Int,
                velocityY: Int
            ): Int {
                currentPosition = super.findTargetSnapPosition(layoutManager, velocityX, velocityY)
                return currentPosition
            }
        }


        bigImgList = findViewById(R.id.big_img_list)
        snapHelper.attachToRecyclerView(bigImgList)
        bigImgList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        bigImgAdapter = BigImgAdapter(this, imgList)
        bigImgList.adapter = bigImgAdapter
        bigImgList.scrollToPosition(imgPosition)
    }

    fun simulation(): ArrayList<String> {
        var imgs: ArrayList<String> = arrayListOf()
        imgs.add("http://static.imjk.club/banner/1606383859810.png?501_240")
        imgs.add("https://static.imjk.club/banner/1609477316017.png?1035_330")
        imgs.add("http://static.imjk.club/banner/1608192107346.png?156_156")
        imgs.add("https://static.imjk.club/banner/1611751859864.png?288_288")
        imgs.add("http://static.imjk.club/banner/1606383859810.png?501_240")
        imgs.add("http://static.imjk.club/banner/1606383859810.png?501_240")
        return imgs
    }

}