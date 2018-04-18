package com.dmi.mvvm_kotlin.view.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.widget.Toast


abstract class BaseViewModel(application: Application): AndroidViewModel(application) {

    protected fun showLoading(){
        Toast.makeText(getApplication(), "loading...", Toast.LENGTH_SHORT).show()
    }

    protected fun dismissLoading(){
        Toast.makeText(getApplication(), "dismiss loading...", Toast.LENGTH_SHORT).show()
    }

    abstract fun onDestroyView()

}
