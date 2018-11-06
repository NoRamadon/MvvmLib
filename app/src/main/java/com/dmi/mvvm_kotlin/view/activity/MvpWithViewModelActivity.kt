package com.dmi.mvvm_kotlin.view.activity

import android.arch.lifecycle.MutableLiveData
import android.os.Bundle
import com.dmi.mvvm_kotlin.R
import com.dmi.mvvm_kotlin.setLiveData
import com.dmi.mvvm_kotlin.view.base.mvp.BaseActivity
import com.dmi.mvvm_kotlin.view.presenter.MvpViewModelPresenter
import com.dmi.mvvm_kotlin.view.presenter.MvpViewModelVista
import kotlinx.android.synthetic.main.activity_mvp_with_view_model.*


class MvpWithViewModelActivity : BaseActivity<MvpViewModelVista.View, MvpViewModelVista.Presenter>(), MvpViewModelVista.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp_with_view_model)

        presenter.bindView(this)

        startTaskButton.setOnClickListener {
            presenter.doSomeTask()
        }
    }

    override fun initPresenter(): MvpViewModelVista.Presenter {
        return MvpViewModelPresenter()
    }

    override fun postTask(liveData: MutableLiveData<String>) {
        timeText.setLiveData(this, liveData)
    }
}
