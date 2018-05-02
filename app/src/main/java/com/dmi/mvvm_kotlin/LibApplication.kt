package com.dmi.mvvm_kotlin

import android.app.Application
import com.dmi.mvvm_kotlin.di.AppComponent
import com.dmi.mvvm_kotlin.di.DaggerAppComponent
import com.dmi.mvvm_kotlin.di.NetModule
import com.dmi.mvvm_kotlin.di.RepositoryModule

class LibApplication: Application() {

    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
                .builder()
                .repositoryModule(RepositoryModule())
                .netModule(NetModule(getString(R.string.base_url)))
                .build()
    }
}