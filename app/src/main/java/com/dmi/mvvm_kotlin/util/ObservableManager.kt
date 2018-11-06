package com.dmi.mvvm_kotlin.util

import com.dmi.mvvm_kotlin.clearOldJob
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*

class ObservableManager {

    var subscriptions = CompositeDisposable()
    val listDisposable = LinkedHashMap<Int, Disposable>()

    fun add(disposable: Disposable) {
        getCompositeDisposable().add(disposable)
    }

    fun singleTask(disposable: Disposable, id: Int){
        listDisposable.clearOldJob(id, disposable)
        getCompositeDisposable().add(disposable)
    }

    fun destroy() {
        getCompositeDisposable().dispose()
        getCompositeDisposable().clear()
        listDisposable.clear()
    }

    private fun getCompositeDisposable(): CompositeDisposable {
        if (subscriptions.isDisposed) {
            subscriptions = CompositeDisposable()
        }
        return subscriptions
    }
}