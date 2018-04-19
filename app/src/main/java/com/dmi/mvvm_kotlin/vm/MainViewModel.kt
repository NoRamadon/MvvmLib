package com.dmi.mvvm_kotlin.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.dmi.mvvm_kotlin.data.repository.UserRepository
import com.dmi.mvvm_kotlin.view.base.BaseViewModel

class MainViewModel(application: Application) : BaseViewModel(application) {

    override fun onDestroyView() {
        dismissLoading()
    }

    private val userRepository = UserRepository()

    val userName = MutableLiveData<String>()

    init {
        showLoading()
        userRepository.getUser().subscribe() {
            userName.postValue(it.name)
            Log.d("post", it.name)
        }
    }

}