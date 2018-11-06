package com.dmi.mvvm_kotlin.view.presenter

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import com.dmi.mvvm_kotlin.view.base.mvp.BaseVista

interface MvpViewModelVista: BaseVista {

    interface View: BaseVista.View {
        fun postTask(liveData: MutableLiveData<String>)
    }

    interface Presenter: BaseVista.Presenter<MvpViewModelVista.View>{
        fun doSomeTask()
    }
}