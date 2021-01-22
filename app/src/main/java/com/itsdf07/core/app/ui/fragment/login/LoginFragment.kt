package com.itsdf07.core.app.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import com.itsdf07.core.app.databinding.FragmentLoginBinding
import com.itsdf07.core.lib.alog.ALog

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/12/25
 */
class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding: FragmentLoginBinding = FragmentLoginBinding.inflate(inflater)
        binding.passwdInput.setOnEditorActionListener { v, actionId, event ->
            ALog.i("actionId:${actionId}")
            when (actionId) {
                EditorInfo.IME_ACTION_GO -> {
                    //TODO 软键盘点击前往，即用来实现登录事件

                }
                else -> {

                }

            }
            return@setOnEditorActionListener false
        }
        return binding.root
    }
}