package com.dmi.mvvm_kotlin.view.fragment


import com.android.databinding.library.baseAdapters.BR
import com.dmi.mvvm_kotlin.R
import com.dmi.mvvm_kotlin.databinding.FragmentTestBinding
import com.dmi.mvvm_kotlin.view.base.BaseFragment
import com.dmi.mvvm_kotlin.vm.TestFragmentViewModel

class TestFragment : BaseFragment<FragmentTestBinding, TestFragmentViewModel>() {

    override val viewModel by lazy { TestFragmentViewModel(activity!!.application) }

    override val bindingVariable by lazy { BR.vm }

    override val layoutId by lazy { R.layout.fragment_test }
}
