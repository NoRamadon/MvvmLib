package com.dmi.mvvm_kotlin.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.dmi.mvvm_kotlin.LibApplication
import com.dmi.mvvm_kotlin.bus.RxBus
import com.dmi.mvvm_kotlin.bus.event.ReplaceFragmentEvent
import com.dmi.mvvm_kotlin.data.model.User
import com.dmi.mvvm_kotlin.data.repository.UserRepository
import com.dmi.mvvm_kotlin.view.base.BaseViewModel
import com.dmi.mvvm_kotlin.view.fragment.LoginFragment
import javax.inject.Inject

class MainViewModel(application: Application) : BaseViewModel(application) {

    @Inject
    lateinit var userRepository: UserRepository

    val user = MutableLiveData<User>()

    init {
        LibApplication.appComponent.inject(this)
        getUserUpdate()
    }

    private fun getUserUpdate() {
        addSubscription(
                userRepository.getUser().subscribe {
                    user.postValue(it)
                })
    }

    fun sendEventOne() {
        RxBus.publish(ReplaceFragmentEvent(LoginFragment()))
    }

    fun sendEventTwo() {
        RxBus.publish(ReplaceFragmentEvent(LoginFragment()))
    }

    override fun onCleared() {
        super.onCleared()

        destroySubscription()
    }
}