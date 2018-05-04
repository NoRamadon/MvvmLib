package com.dmi.mvvm_kotlin.data.remote

import com.dmi.mvvm_kotlin.data.model.MapResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface WebService {

    @GET("geocode/json?")
    fun getAddress(@Query("latlng") latlng: String): Single<MapResponse>
}