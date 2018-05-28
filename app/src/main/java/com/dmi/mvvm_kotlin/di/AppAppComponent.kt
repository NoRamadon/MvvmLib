package com.dmi.mvvm_kotlin.di

import com.dmi.mvvm_kotlin.data.usecase.MapUseCase
import com.dmi.mvvm_kotlin.vm.MainViewModel
import com.dmi.mvvm_kotlin.vm.StoreFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, NetModule::class, DataBaseModule::class])
interface AppComponent {
    fun inject(mainViewModel: MainViewModel)
    fun inject(storeFragmentViewModel: StoreFragmentViewModel)
    fun inject(mapUseCase: MapUseCase)
}