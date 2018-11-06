package com.dmi.mvvm_kotlin.view.base.mvp

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<V: BaseVista.View, P: BaseVista.Presenter<V>>: AppCompatActivity(), BaseVista.View {

    protected lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val baseMvpViewModel = ViewModelProviders.of(this).get(BaseMvpViewModel::class.java as Class<BaseMvpViewModel<V, P>>)
        baseMvpViewModel.bindPresenter(initPresenter())
        presenter = baseMvpViewModel.getPresenter()
    }

    protected abstract fun initPresenter(): P
}