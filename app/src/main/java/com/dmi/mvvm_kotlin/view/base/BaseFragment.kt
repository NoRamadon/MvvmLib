package com.dmi.mvvm_kotlin.view.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dmi.mvvm_kotlin.R
import com.dmi.mvvm_kotlin.bus.RxBus
import com.dmi.mvvm_kotlin.bus.event.AddFragmentEvent
import com.dmi.mvvm_kotlin.bus.event.ReplaceFragmentEvent
import com.dmi.mvvm_kotlin.bus.event.RxBusEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    private lateinit var mRootView: View
    private lateinit var mViewDataBinding: B
    private var mSubscriptions = CompositeDisposable()

    /**
     * Override for view model
     *
     */
    protected abstract val viewModel: VM

    /**
     * Override for binding variable
     *
     */
    protected abstract val bindingVariable: Int

    /**
     * @return layout resource id
     */
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // retainInstance = true //make live data survive on configuration change
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mViewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mRootView = mViewDataBinding.root
        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewDataBinding.setLifecycleOwner(activity)
        mViewDataBinding.setVariable(bindingVariable, viewModel)
        mViewDataBinding.executePendingBindings()

        viewModel.onActivityCreated()
    }

    override fun onPause() {
        super.onPause()
        mSubscriptions.clear()
    }

    override fun onResume() {
        super.onResume()
        registerEvent()
    }

    private fun registerEvent(){
        mSubscriptions.addAll(registerBus())
    }

    private fun registerBus(): Disposable{
        return RxBus.listen(RxBusEvent::class.java).subscribe {
            when(it){
                is ReplaceFragmentEvent -> replaceFragment(it.fragment)
                is AddFragmentEvent -> addFragment(it.fragment)
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.mainContent, fragment)
                .commit()
    }

    private fun addFragment(fragment: Fragment){
        activity!!.supportFragmentManager.beginTransaction()
                .add(R.id.mainContent, fragment)
                .addToBackStack(fragment.tag)
                .commit()
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("status: ", "onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Log.d("status: ", "onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()

        Log.d("status: ", "onDetach")
    }
}
