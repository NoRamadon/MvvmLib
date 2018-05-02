package com.dmi.mvvm_kotlin.di

import com.dmi.mvvm_kotlin.data.usecase.MapUseCase
import com.dmi.mvvm_kotlin.vm.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, NetModule::class])
interface AppComponent {
    fun inject(mainViewModel: MainViewModel)
    fun inject(mapUseCase: MapUseCase)
}