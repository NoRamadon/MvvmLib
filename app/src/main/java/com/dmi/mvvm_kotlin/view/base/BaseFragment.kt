package com.dmi.mvvm_kotlin.view.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


abstract class BaseFragment<B : ViewDataBinding, out VM : BaseViewModel> : Fragment() {

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    protected abstract val viewModel: VM

    private lateinit var mRootView: View
    private lateinit var mViewDataBinding: B

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mRootView = mViewDataBinding.root
        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewDataBinding.setLifecycleOwner(this)
        mViewDataBinding.setVariable(getBindingVariable(), viewModel)
        mViewDataBinding.executePendingBindings()
    }
}
