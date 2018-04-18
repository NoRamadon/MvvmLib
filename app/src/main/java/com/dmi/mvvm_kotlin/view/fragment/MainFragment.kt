package com.dmi.mvvm_kotlin.view.fragment

import com.android.databinding.library.baseAdapters.BR
import com.dmi.mvvm_kotlin.R
import com.dmi.mvvm_kotlin.databinding.FragmentMainBinding
import com.dmi.mvvm_kotlin.view.base.BaseFragment
import com.dmi.mvvm_kotlin.vm.MainViewModel

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override fun getViewModel(): MainViewModel {
        return MainViewModel(activity!!.application)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun getBindingVariable(): Int {
        return BR.vm
    }
}
