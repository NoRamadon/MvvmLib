package com.dmi.mvvm_kotlin.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.dmi.mvvm_kotlin.LibApplication
import com.dmi.mvvm_kotlin.data.repository.UserRepository
import com.dmi.mvvm_kotlin.view.base.BaseViewModel
import javax.inject.Inject

class MainViewModel(application: Application) : BaseViewModel(application) {

    @Inject
    lateinit var userRepository: UserRepository

    val userName = MutableLiveData<String>()

    init {
        LibApplication.appComponent.inject(this)
        getUserUpdate()
    }

    override fun onCreate() {
        //getUserUpdate()
    }

    private fun getUserUpdate() {
        addSubscription(
                userRepository.getUser().subscribe {
                    userName.postValue(it.name)
                    Log.d("post", it.name)
                })
    }

    fun reCreateSubscription(){
        getUserUpdate()
    }

    fun removeSubscription(){
        clearSubscription()
    }
}