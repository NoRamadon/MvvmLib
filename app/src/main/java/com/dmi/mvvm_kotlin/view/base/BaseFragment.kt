package com.dmi.mvvm_kotlin.view.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dmi.mvvm_kotlin.R
import com.dmi.mvvm_kotlin.bus.RxBus
import com.dmi.mvvm_kotlin.bus.event.ReplaceFragmentEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseFragment<B : ViewDataBinding, out VM : BaseViewModel> : Fragment() {

    private lateinit var mRootView: View
    private lateinit var mViewDataBinding: B
    private var mSubscriptions = CompositeDisposable()

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    protected abstract val viewModel: VM

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

    override fun onPause() {
        super.onPause()
        mSubscriptions.clear()
    }

    override fun onResume() {
        super.onResume()
        registerBus()
    }

    private fun registerBus(){
        mSubscriptions.addAll(replaceFragment())
    }

    private fun replaceFragment(): Disposable{
        return RxBus.listen(ReplaceFragmentEvent::class.java).subscribe({
            activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContent, it.fragment)
                    .commit()
        })
    }
}
