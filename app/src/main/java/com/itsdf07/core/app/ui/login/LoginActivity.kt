package com.itsdf07.core.app.ui.login

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.itsdf07.core.app.MainActivity

import com.itsdf07.core.app.R
import com.itsdf07.core.app.databinding.ActivityLoginBinding
import com.itsdf07.core.app.view.TitleBar
import com.itsdf07.core.lib.alog.ALog

class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var dataBinding: ActivityLoginBinding

    /**
     * 页面的标题栏
     */
    private lateinit var titleBar: TitleBar

    /**
     * 账号输入控件
     */
    private lateinit var etLoginId: EditText

    /**
     * 密码输入控件
     */
    private lateinit var etPasswd: EditText

    /**
     * 登录按钮
     */
    private lateinit var btnLogin: Button

    /**
     * 登录过程的进度条
     */
    private lateinit var pbLoading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

        loginViewModel =
            ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)

        //lifedata观察dataLogin数据的变化，同时通过databinding与布局绑定进行UI刷新
        loginViewModel.dataLogin.observe(this@LoginActivity, Observer {
            dataBinding.dataLogin = it
        })

        //lifedata观察dataLogin数据的变化，同时通过databinding与布局绑定进行UI刷新
        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            //判断：当LoginFormState对象为null时结束当前观察
            val loginState = it ?: return@Observer
            // disable login button unless both username / password is valid
            btnLogin.isEnabled = loginState.isDataValid//设置登录按钮是可用：受用户名、密码规则决定
            //设置用户名、密码输入框的检测状态，比如用户名格式异常或者密码不匹配等提示
            if (loginState.usernameError != null) {
                etLoginId.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                etPasswd.error = getString(loginState.passwordError)
            }

        })
        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            //在点击登录的时候开始显示登录进度条，登录结束当然要小时，即隐藏
            pbLoading.visibility = View.GONE

            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
//            setResult(Activity.RESULT_OK)

            startActivity(Intent(this, MainActivity::class.java))
            //Complete and destroy login activity once successful
            finish()
        })

        etLoginId.afterTextChanged {
            loginViewModel.loginDataChanged(
                etLoginId.text.toString(),
                etPasswd.text.toString()
            )
        }

        etPasswd.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    etLoginId.text.toString(),
                    etPasswd.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            etLoginId.text.toString(),
                            etPasswd.text.toString()
                        )
                }
                false
            }

            btnLogin.setOnClickListener {
                pbLoading.visibility = View.VISIBLE
                loginViewModel.login(etLoginId.text.toString(), etPasswd.text.toString())
            }
        }
        titleBar.apply {
            setOnBackClickListener {
                finish()
            }
            setOnShareClickListener {
                Toast.makeText(
                    applicationContext, "分享功能尚未实现", Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun initView() {
        dataBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        titleBar = dataBinding.titlebar
        etLoginId = dataBinding.loginId
        etPasswd = dataBinding.password
        btnLogin = dataBinding.login
        pbLoading = dataBinding.loading
    }

    /**
     * 刷新用户登录成功的相关UI展示，启动成功登录相关流程
     * @param model 成功登录的用户可见信息
     */
    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext, "$welcome $displayName", Toast.LENGTH_LONG
        ).show()
    }

    /**
     * 提示登录失败的相关信息
     * @param errorString 失败信息
     */
    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

