package com.itsdf07.core.app.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.itsdf07.core.app.data.LoginRepository
import com.itsdf07.core.app.data.Result

import com.itsdf07.core.app.R
import com.itsdf07.core.lib.alog.ALog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    private val TAG = "LoginViewModel"
    val dataLogin = MutableLiveData<DataLogin>().apply {
        //TODO 此处可以处理本地已登陆过的账号记录
        var loginId = "aso1"
        var passwd = "123456"
        var appInfo = "当前版本：1.0"
        value = DataLogin(loginId, passwd, appInfo)
//        GlobalScope.launch(Dispatchers.IO) {
//            repeat(3) {
//                ALog.d("it:${it}")
//                delay(3000)
//                value!!.loginId += it
//                value!!.passwd += it
//                value!!.appInfo += it
//                postValue(value)
//            }
//        }
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

    fun login() {
        // can be launched in a separate asynchronous job
        val result = loginRepository.login(dataLogin.value!!.loginId, dataLogin.value!!.passwd)

        if (result is Result.Success) {
            _loginResult.value =
                LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun loginDataChanged(username: String, password: String) {
        ALog.dTag(TAG, "username:${username},password:${password}")
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return username.isNotEmpty()
//        return if (username.contains('@')) {
//            Patterns.EMAIL_ADDRESS.matcher(username).matches()
//        } else {
//            username.isNotBlank()
//        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}