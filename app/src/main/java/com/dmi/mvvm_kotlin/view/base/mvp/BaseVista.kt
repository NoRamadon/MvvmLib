package com.dmi.mvvm_kotlin.view.base.mvp

import io.reactivex.disposables.Disposable

interface BaseVista {
    interface View

    interface Presenter<V: BaseVista.View>{
        fun bindView(view: V)
        fun unbindView()
        fun getView(): V?
        fun addSubscription(disposable: Disposable)
        fun onDestroyPresenter()
    }

}