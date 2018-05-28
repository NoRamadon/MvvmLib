package com.dmi.mvvm_kotlin

import android.app.Application
import com.dmi.mvvm_kotlin.di.*

open class LibApplication: Application() {

    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
                .builder()
                .repositoryModule(RepositoryModule())
                .netModule(NetModule(getString(R.string.base_url)))
                .dataBaseModule(DataBaseModule(this))
                .build()
    }
}