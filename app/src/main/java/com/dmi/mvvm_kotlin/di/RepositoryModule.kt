package com.dmi.mvvm_kotlin.di

import com.dmi.mvvm_kotlin.data.repository.DownloadRepository
import com.dmi.mvvm_kotlin.data.repository.UserRepository
import com.dmi.mvvm_kotlin.data.repository.model.FileDownloader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository = UserRepository()

    @Provides
    @Singleton
    fun providesDownloadRepository(): DownloadRepository = FileDownloader()
}