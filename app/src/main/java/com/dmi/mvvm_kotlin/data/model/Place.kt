package com.dmi.mvvm_kotlin.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


data class Place(val results: List<Result>, val status: String, val error_message: String)

@Entity(tableName = "Place")
data class Result(val name: String){
    @PrimaryKey(autoGenerate = true) var placeId: Long = 0
}