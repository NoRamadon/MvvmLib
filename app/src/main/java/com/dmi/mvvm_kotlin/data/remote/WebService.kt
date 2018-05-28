package com.dmi.mvvm_kotlin.data.remote

import com.dmi.mvvm_kotlin.data.model.MapResponse
import com.dmi.mvvm_kotlin.data.model.Place
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface WebService {

    @GET("geocode/json?")
    fun getAddress(@Query("latlng") latlng: String): Single<MapResponse>

    @GET("place/nearbysearch/json?")
    fun getNearBy(@Query("location") latlng: String = "-33.8670522,151.1957362",
                  @Query("radius") radius: String = "500",
                  @Query("type") type: String = "restaurant",
                  @Query("key") key: String = "AIzaSyA0wmSS4tstTuEnWek2nIQiJEKuVPDopIU"): Single<Place>
}