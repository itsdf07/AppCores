package com.itsdf07.core.app.ui.activity.ad

import android.Manifest
import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.itsdf07.core.app.R
import com.itsdf07.core.app.ui.activity.ad.ADGloadConfig.TAG_AD
import com.itsdf07.core.app.ui.navidrawer.MainDrawerNaviActivity
import com.itsdf07.core.lib.alog.ALog
import com.qq.e.ads.splash.SplashAD
import com.qq.e.ads.splash.SplashADListener
import com.qq.e.ads.splash.SplashADZoomOutListener
import com.qq.e.comm.util.AdError

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/2/10
 */
class SplashActivity : AppCompatActivity(),
    SplashADZoomOutListener //开屏V+广告，即基于普通的开屏广告基础上，增加  5s-30s 的视频广告，在5s开屏呈现的过程中用户点击右上角的“进入首页”或5s曝光结束后视频将收缩到APP内右下角的小视窗继续播放
{

    lateinit var adContainer: FrameLayout

    lateinit var splashAd: SplashAD
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
        fetchSplashAD(this, adContainer, skipView, getPosId(), this, 5000)
    }


    fun fetchSplashAD(
        context: Context,
        adContainer: ViewGroup,
        skipView: TextView,
        posId: String,
        adListener: SplashADListener,
        fetchDelay: Int
    ) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkAndRequestPermission()) {
                splashAd = if (customSkipBtn == null) {
                    SplashAD(context, skipView, posId, adListener, fetchDelay)
                } else {
                    SplashAD(context, posId, adListener, fetchDelay)
                }
                splashAd.fetchAndShowIn(adContainer)
            } else {

            }
        } else {
            splashAd = if (customSkipBtn == null) {
                SplashAD(context, skipView, posId, adListener, fetchDelay)
            } else {
                SplashAD(context, posId, adListener, fetchDelay)
            }
            splashAd.fetchAndShowIn(adContainer)
        }

    }

    /**
     * ----------非常重要----------
     * Android6.0以上的权限适配简单示例：
     * 如果targetSDKVersion >= 23，那么建议动态申请相关权限，再调用广点通SDK
     * SDK不强制校验下列权限（即:无下面权限sdk也可正常工作），但建议开发者申请下面权限，尤其是READ_PHONE_STATE权限
     * READ_PHONE_STATE权限用于允许SDK获取用户标识,
     * 针对单媒体的用户，允许获取权限的，投放定向广告；不允许获取权限的用户，投放通投广告，媒体可以选择是否把用户标识数据提供给优量汇，并承担相应广告填充和eCPM单价下降损失的结果。
     * Demo代码里是一个基本的权限申请示例，请开发者根据自己的场景合理地编写这部分代码来实现权限申请。
     * 注意：下面的`checkSelfPermission`和`requestPermissions`方法都是在Android6.0的SDK中增加的API，如果您的App还没有适配到Android6.0以上，则不需要调用这些方法，直接调用广点通SDK即可。
     */
    @TargetApi(Build.VERSION_CODES.M)
    fun checkAndRequestPermission(): Boolean {
        var lackedPermission = arrayListOf<String>()
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            lackedPermission.add(Manifest.permission.READ_PHONE_STATE)
        }
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            lackedPermission.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            lackedPermission.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            lackedPermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        return if (lackedPermission.size == 0) {
            true
        } else {

            // 否则，建议请求所缺少的权限，在onRequestPermissionsResult中再看是否获得权限
            val requestPermissions = arrayOfNulls<String>(lackedPermission.size)
            lackedPermission.toArray(requestPermissions)
            requestPermissions(requestPermissions, 1024)
            false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1024 && hasAllPermissionsGranted(grantResults)) {
            fetchSplashAD(this, adContainer, skipView, getPosId(), this, 5000)
        } else {
            Toast.makeText(this, "应用缺少必要的权限！请点击\"权限\"，打开所需要的权限。", Toast.LENGTH_LONG).show()
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.parse("package:$packageName")
            startActivity(intent)
            finish()
        }
    }

    private fun hasAllPermissionsGranted(grantResults: IntArray): Boolean {
        for (grantResult in grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false
            }
        }
        return true
    }

    private fun getPosId(): String {
        return "2031959868577560"
//        return "2001447730515391"
    }
    //--------------------------------------------------------------------

    /**
     * 在使用SplashADZoomOutListener接口时不支持开屏V+广告可以在这里返回false
     */
    override fun isSupportZoomOut(): Boolean {
        ALog.iTag(TAG_AD, "isSupportZoomOut:${isSupportZoomOut}")
        return isSupportZoomOut
    }

    /**
     * 广告成功展示时调用，成功展示不等于有效展示（比如广告容器高度不够）
     */
    override fun onADPresent() {
        ALog.iTag(TAG_AD, "......")
    }

    /**
     * 广告加载成功的回调，在fetchAdOnly的情况下，表示广告拉取成功可以显示了。广告需要在SystemClock.elapsedRealtime <expireTimestamp前展示，否则在showAd时会返回广告超时错误
     */
    override fun onADLoaded(expireTimestamp: Long) {
        ALog.iTag(TAG_AD, "expireTimestamp:${expireTimestamp}")
    }

    /**
     * 开屏V+广告开始进入小窗悬挂状态，收到该回调时可以进行动画缩小广告的播放界面，动画完成后要调用zoomOutAnimationFinish
     */
    override fun onZoomOut() {
        ALog.iTag(TAG_AD, "......")
    }

    /**
     * 广告曝光时调用
     */
    override fun onADExposure() {
        ALog.iTag(TAG_AD, "......")
    }

    /**
     * 广告关闭时调用，可能是用户关闭或者展示时间到。此时一般需要跳过开屏的 Activity，进入应用内容页面
     */
    override fun onADDismissed() {
        ALog.iTag(TAG_AD, "......")

        startActivity(Intent(this, MainDrawerNaviActivity::class.java))
    }

    /**
     * 广告加载失败，error 对象包含了错误码和错误信息，错误码的详细内容可以参考文档第5章
     */
    override fun onNoAD(error: AdError) {
        ALog.iTag(TAG_AD, "error:${error.errorCode}-${error.errorMsg}-")
    }

    /**
     * 广告被点击时调用，不代表满足计费条件（如点击时网络异常）
     */
    override fun onADClicked() {
        ALog.iTag(TAG_AD, "......")
    }

    /**
     * 倒计时回调，返回广告还将被展示的剩余时间，单位是 ms
     */
    override fun onADTick(millisUntilFinished: Long) {
        ALog.iTag(TAG_AD, "millisUntilFinished:${millisUntilFinished}")
    }

    /**
     * 开屏V+的视频播放结束
     */
    override fun onZoomOutPlayFinish() {
        ALog.iTag(TAG_AD, "......")
    }
}