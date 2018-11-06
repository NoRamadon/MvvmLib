package com.dmi.mvvm_kotlin.view.base.mvp

import com.dmi.mvvm_kotlin.util.ObservableManager
import io.reactivex.disposables.Disposable

abstract class BasePresenter<V: BaseVista.View>: BaseVista.Presenter<V> {

    private val observableManager = ObservableManager()
    private  var view: V? = null

    override fun getView(): V {
        return view!!
    }

    override fun bindView(view: V) {
        this.view = view
    }

    override fun unbindView() {
        view = null
    }

    override fun addSubscription(disposable: Disposable) {
        observableManager.add(disposable)
    }

    override fun onDestroyPresenter() {
        observableManager.destroy()
    }

}