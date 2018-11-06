package com.dmi.mvvm_kotlin.di

import android.arch.lifecycle.ViewModel
import com.dmi.mvvm_kotlin.vm.DownloadTaskViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass



@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DownloadTaskViewModel::class)
    abstract fun bindCryptoListViewModel(viewModel: DownloadTaskViewModel) : ViewModel
}