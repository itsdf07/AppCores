package com.itsdf07.core.app.ui.navi

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.itsdf07.core.app.R
import com.itsdf07.core.lib.alog.ALog


/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/3/3
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn1).setOnClickListener {
            checkPermissioin()
        }
    }

    private val REQUEST_CODE_ADDRESS = 100

    private fun checkPermissioin() {
        val checkCoarse: Int = ContextCompat.checkSelfPermission(
            this@MainActivity,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        val checkCoarseFine: Int = ContextCompat.checkSelfPermission(
            this@MainActivity,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        ALog.dTag("Permissions", "权限，checkCoarse：%s,checkCoarseFine：%s",checkCoarse,checkCoarseFine)
        if (checkCoarse == PackageManager.PERMISSION_GRANTED && checkCoarseFine == PackageManager.PERMISSION_GRANTED) {
            //已经授权
        } else { //没有权限
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf<String>(
                    Manifest.permission.ACCESS_COARSE_LOCATION
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
        ALog.dTag(
            "Permissions",
            "requestCode：%s,permissions：%s,grantResults:%s",
            requestCode,
            permissions,
            grantResults
        )
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}