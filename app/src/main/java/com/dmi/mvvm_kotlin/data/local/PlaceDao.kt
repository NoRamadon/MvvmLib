package com.dmi.mvvm_kotlin.data.local

import android.arch.paging.DataSource
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.dmi.mvvm_kotlin.data.model.Place
import com.dmi.mvvm_kotlin.data.model.Result

@Dao interface PlaceDao {

    @Query("select * from Place ORDER BY placeId DESC")
    fun getAllPlace():  DataSource.Factory<Int, Result>

    /*@Query("select * from Place where id = :p0")
    fun findPlaceById(id: Long): Place*/

    @Insert(onConflict = REPLACE)
    fun insertPlace(place: Result)

   /* @Update(onConflict = REPLACE)
    fun updatePlace(place: Place)*/

    @Query("DELETE FROM Place")
    fun deletePreviousPlace()
}