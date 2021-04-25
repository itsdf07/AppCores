package com.itsdf07.core.app.example

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.itsdf07.core.app.R


/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/4/25
 */
class StartAnotherAppActivity : AppCompatActivity() {
    private val TAG = "StartAnotherAppActivity"
    private lateinit var inputAppInfo: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exapmle_start_another_app)
        inputAppInfo = findViewById(R.id.input_app_input)
        findViewById<Button>(R.id.btn_check_app_installed).setOnClickListener {
            var isInstall = checkPackInfo("com.itsdf07.app2")
            Log.d(TAG, "isInstall:$isInstall")
        }
        findViewById<Button>(R.id.btn_start_app1).setOnClickListener {
            var intent = Intent(Intent.ACTION_MAIN)
            var componentName = ComponentName("com.itsdf07.app2", "com.itsdf07.app2.MainActivity")
            intent.component = componentName
            startActivity(intent)
        }
        findViewById<Button>(R.id.btn_start_app2).setOnClickListener {
            var intent = packageManager.getLaunchIntentForPackage("com.itsdf07.app2")
            if (intent != null) {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }
        findViewById<Button>(R.id.btn_start_app3).setOnClickListener {
            var intent = Intent()
            intent.data = Uri.parse("aso://itsdf07.top/itsdf07")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)

        }

    }

    /**
     * 检查包是否存在
     *
     * @param packname
     * @return
     */
    private fun checkPackInfo(packname: String): Boolean {
        var packageInfo: PackageInfo? = null
        try {
            packageInfo = packageManager.getPackageInfo(packname, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return packageInfo != null
    }

}