package com.dmi.mvvm_kotlin.view.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.widget.Toast
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseViewModel(application: Application): AndroidViewModel(application) {

    companion object {
        private var mSubscriptions = CompositeDisposable()
    }


    protected fun showLoading(){
        Toast.makeText(getApplication(), "loading...", Toast.LENGTH_SHORT).show()
    }

    protected fun dismissLoading(){
        Toast.makeText(getApplication(), "dismiss loading...", Toast.LENGTH_SHORT).show()
    }

    protected fun addSubscription(disposable: Disposable) {
        mSubscriptions.clear()
        mSubscriptions.addAll(disposable)
    }

    protected fun clearSubscription(){
        mSubscriptions.clear()
    }

    abstract fun onCreate()

    fun onDestroyView(){
        clearSubscription()
    }

}
