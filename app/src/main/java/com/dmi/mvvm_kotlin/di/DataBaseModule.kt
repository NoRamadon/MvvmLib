package com.dmi.mvvm_kotlin.di

import android.arch.persistence.room.Room
import android.content.Context
import com.dmi.mvvm_kotlin.data.local.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule(private val context: Context) {

    @Provides
    fun providesAppContext() = context

    @Provides fun providesAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "my-db").allowMainThreadQueries().build()

    @Provides fun providesToDoDao(database: AppDatabase) = database.taskDao()
}
