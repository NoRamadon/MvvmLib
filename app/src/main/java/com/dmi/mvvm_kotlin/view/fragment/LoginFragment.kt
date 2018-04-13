package com.dmi.mvvm_kotlin.view.fragment

import android.os.Bundle
import android.view.View
import com.android.databinding.library.baseAdapters.BR

import com.dmi.mvvm_kotlin.R
import com.dmi.mvvm_kotlin.databinding.FragmentLoginBinding
import com.dmi.mvvm_kotlin.view.base.BaseFragment
import com.dmi.mvvm_kotlin.vm.LoginViewModel


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    override fun getBindingVariable(): Int {
        return BR.vm
    }

    override fun getViewModel(): LoginViewModel {
        return LoginViewModel()
    }
}
