package com.dmi.mvvm_kotlin.view.presenter

import android.arch.lifecycle.MutableLiveData
import com.dmi.mvvm_kotlin.view.base.mvp.BasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class MvpViewModelPresenter: BasePresenter<MvpViewModelVista.View>(), MvpViewModelVista.Presenter {

    private val timeLiveData = MutableLiveData<String>()

    override fun doSomeTask() {
        addSubscription(Observable.interval(1, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    timeLiveData.postValue(Calendar.getInstance().time.toString())
                    getView().postTask(timeLiveData)
                })
    }
}