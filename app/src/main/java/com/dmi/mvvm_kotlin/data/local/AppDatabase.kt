package com.dmi.mvvm_kotlin.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.dmi.mvvm_kotlin.data.model.Result

@Database(entities = arrayOf(Result::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): PlaceDao
}
