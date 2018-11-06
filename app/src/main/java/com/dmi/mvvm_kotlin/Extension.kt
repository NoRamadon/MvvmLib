package com.dmi.mvvm_kotlin

import android.app.Activity
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.widget.TextView
import io.reactivex.disposables.Disposable


fun LinkedHashMap<Int, Disposable>.clearOldJob(id: Int, disposable: Disposable){
    for (item in this){
        if (item.key == id){
            item.value.dispose()
        }
    }
    this[id] = disposable
}

fun TextView.setLiveData(lifecycleOwner: LifecycleOwner, liveData: MutableLiveData<String>){
    liveData.observe(lifecycleOwner, Observer {
        this.text = it
    })
}