package com.itsdf07.core.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.itsdf07.core.lib.alog.ALog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ALog.dTag("TAG-Main","%s","这是测试ALog打印")
    }
}