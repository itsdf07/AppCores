package com.itsdf07.core.app.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itsdf07.core.app.data.TabLayoutBean
import com.itsdf07.core.app.ui.home.HomeFragment
import com.itsdf07.core.lib.alog.ALog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {
    /**
     * ViewPager中对应的页面集合
     */
    public var fragments: MutableLiveData<ArrayList<TabLayoutBean>> = MutableLiveData()

    private val TAG = "LoginViewModel"
    val dataLogin = MutableLiveData<DataLogin>().apply {
        //TODO 此处可以处理本地已登陆过的账号记录
        var loginId = "aso1"
        var passwd = "123456"
        var appInfo = "当前版本：1.0"
        value = DataLogin(loginId, passwd, appInfo)
        GlobalScope.launch(Dispatchers.IO) {
            repeat(3) {
                ALog.d("it:${it}")
                delay(3000)
                value!!.loginId += it
                value!!.passwd += it
                value!!.appInfo += it
                postValue(value)
            }
        }
    }

    private val _loginForm = MutableLiveData<LoginFormState>()

    /**
     * 登录前的数据状态
     */
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()

    /**
     * 登录事件后的登陆结果
     */
    val loginResult: LiveData<LoginResult> = _loginResult


    /**
     * ViewPager中对应的页面集合
     */
    fun loadFragments(): MutableLiveData<ArrayList<TabLayoutBean>> {
        fragments.value = arrayListOf()
        fragments.value!!.add(TabLayoutBean().apply {
            tabTitle = "登录"
            fragment = HomeFragment()
        })
        fragments.value!!.add(TabLayoutBean().apply {
            tabTitle = "注册"
            fragment = HomeFragment()
        })

        return fragments
    }
}