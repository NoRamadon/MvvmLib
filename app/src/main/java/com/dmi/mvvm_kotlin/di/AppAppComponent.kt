package com.dmi.mvvm_kotlin.di

import com.dmi.mvvm_kotlin.vm.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(RepositoryModule::class)])
interface AppComponent {

    fun inject(mainViewModel: MainViewModel)
}