package com.dmi.mvvm_kotlin.view.fragment

import com.android.databinding.library.baseAdapters.BR
import com.dmi.mvvm_kotlin.R
import com.dmi.mvvm_kotlin.databinding.FragmentLoginBinding
import com.dmi.mvvm_kotlin.view.base.BaseFragment
import com.dmi.mvvm_kotlin.vm.LoginViewModel


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel by lazy {  LoginViewModel(activity!!.application)}

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    override fun getBindingVariable(): Int {
        return BR.vm
    }
}
