package com.itsdf07.core.app.ui.navi

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.itsdf07.core.app.R
import com.itsdf07.core.lib.alog.ALog
import com.itsdf07.core.lib.common.DeviceUtils
import org.w3c.dom.Text


/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/3/3
 */
class MainActivity : AppCompatActivity() {
    lateinit var deviceInfo: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        deviceInfo = findViewById(R.id.deviceInfo)
        checkPermissioin()
    }

    private val REQUEST_CODE_ADDRESS = 100

    private fun checkPermissioin() {
        val checkCoarse: Int = ContextCompat.checkSelfPermission(
            this@MainActivity,
            Manifest.permission.READ_PHONE_STATE
        )
        ALog.dTag(
            "Permissions",
            "权限，checkCoarse：%s",
            checkCoarse
        )
        if (checkCoarse == PackageManager.PERMISSION_GRANTED
        ) {
            var stringBuffer: StringBuffer = StringBuffer()
            stringBuffer.append("AndroidId:" + DeviceUtils.getAndroidID(this) + "\n")
            stringBuffer.append("IMEI:" + DeviceUtils.getImei(this) + "\n")
            stringBuffer.append("IMSI:" + DeviceUtils.getImsi(this) + "\n")
            stringBuffer.append("厂商:" + DeviceUtils.getBrand() + "\n")
            stringBuffer.append("型号:" + DeviceUtils.getModel() + "\n")
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_PHONE_STATE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            stringBuffer.append("序列号:" + DeviceUtils.getSerial() + "\n")
            stringBuffer.append("制造商:" + DeviceUtils.getAndroidID(this) + "\n")
            deviceInfo.text = stringBuffer.toString()
        } else { //没有权限
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf<String>(
                    Manifest.permission.READ_PHONE_STATE
//                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                REQUEST_CODE_ADDRESS
            ) //申请授权
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        ALog.dTag(
            "Permissions",
            "requestCode：%s,permissions：%s,grantResults:%s",
            requestCode,
            permissions,
            grantResults
        )
        var stringBuffer: StringBuffer = StringBuffer()
        stringBuffer.append("AndroidId:" + DeviceUtils.getAndroidID(this) + "\n")
        stringBuffer.append("IMEI:" + DeviceUtils.getImei(this) + "\n")
        stringBuffer.append("IMSI:" + DeviceUtils.getImsi(this) + "\n")
        stringBuffer.append("厂商:" + DeviceUtils.getBrand() + "\n")
        stringBuffer.append("型号:" + DeviceUtils.getModel() + "\n")
        stringBuffer.append("序列号:" + DeviceUtils.getSerial() + "\n")
        stringBuffer.append("制造商:" + DeviceUtils.getAndroidID(this) + "\n")
        deviceInfo.text = stringBuffer.toString()

    }
}