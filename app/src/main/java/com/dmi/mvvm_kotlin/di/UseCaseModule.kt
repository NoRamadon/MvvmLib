package com.dmi.mvvm_kotlin.di

import com.dmi.mvvm_kotlin.data.repository.DownloadRepository
import com.dmi.mvvm_kotlin.data.usecase.DownloadInteractor
import com.dmi.mvvm_kotlin.data.usecase.DownloadUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideDownloadUseCase(repository: DownloadRepository): DownloadUseCase = DownloadInteractor(repository)
}