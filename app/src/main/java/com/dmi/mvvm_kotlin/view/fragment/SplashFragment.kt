package com.dmi.mvvm_kotlin.view.fragment

import com.android.databinding.library.baseAdapters.BR
import com.dmi.mvvm_kotlin.R
import com.dmi.mvvm_kotlin.databinding.FragmentSplashBinding
import com.dmi.mvvm_kotlin.view.base.BaseFragment
import com.dmi.mvvm_kotlin.vm.SplashViewModel


class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    override val viewModel by lazy { SplashViewModel(activity!!.application) }

    override val bindingVariable by lazy { BR.vm }

    override val layoutId by lazy { R.layout.fragment_splash }

}
