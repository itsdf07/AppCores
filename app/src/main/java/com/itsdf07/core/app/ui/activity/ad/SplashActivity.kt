package com.itsdf07.core.app.ui.activity.ad

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.itsdf07.core.app.R

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/2/10
 */
class SplashActivity : AppCompatActivity() {

    lateinit var adContainer: FrameLayout

    lateinit var skipView: TextView

    /**
     * 是否支持开屏V+广告，true-支持，false不支持
     */
    private var isSupportZoomOut = false
    private var customSkipBtn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        adContainer = findViewById(R.id.ad_container)
        skipView = findViewById(R.id.skip_view)
    }

}