package com.dmi.mvvm_kotlin.view.base.mvp

import android.arch.lifecycle.ViewModel

class BaseMvpViewModel<V: BaseVista.View, P: BaseVista.Presenter<V>>: ViewModel() {

    private lateinit var presenter: P

    fun bindPresenter(presenter: P){
        this.presenter = presenter
    }

    fun getPresenter(): P {
       return this.presenter
    }

    override fun onCleared() {
        super.onCleared()

        presenter.onDestroyPresenter()
    }
}