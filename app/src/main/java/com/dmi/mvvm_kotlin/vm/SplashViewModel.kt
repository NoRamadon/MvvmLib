package com.dmi.mvvm_kotlin.vm

import android.app.Application
import com.dmi.mvvm_kotlin.bus.RxBus
import com.dmi.mvvm_kotlin.bus.event.ReplaceFragmentEvent
import com.dmi.mvvm_kotlin.view.base.BaseViewModel
import com.dmi.mvvm_kotlin.view.fragment.MainFragment
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


class SplashViewModel(application: Application) : BaseViewModel(application) {

    override fun onActivityCreated() {
        super.onActivityCreated()
        startSplash()
    }

    private fun startSplash(){
        Observable.timer(3, TimeUnit.SECONDS).subscribe {
            RxBus.publish(ReplaceFragmentEvent(MainFragment()))
        }
    }
}