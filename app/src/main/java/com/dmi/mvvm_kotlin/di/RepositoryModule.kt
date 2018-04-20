package com.dmi.mvvm_kotlin.di

import com.dmi.mvvm_kotlin.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository = UserRepository()
}