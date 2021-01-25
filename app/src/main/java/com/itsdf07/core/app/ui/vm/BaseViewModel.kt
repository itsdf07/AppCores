package com.itsdf07.core.app.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/7
 */
open class BaseViewModel : ViewModel() {
    /**
     * 用于网络请求时，相关的UI线程通知驱动
     */
    var netNotifyLifeData: MutableLiveData<Notify2UILifeDataBean> = MutableLiveData()
}