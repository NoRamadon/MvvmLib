package com.dmi.mvvm_kotlin.data.remote

import com.dmi.mvvm_kotlin.data.model.GoogleMapResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WebService {

    @GET("geocode/json?")
    fun getAddress(@Query("latlng") latlng: String): Observable<GoogleMapResponse>

    @GET("geocode/json?")
    fun getCall(): Call<GoogleMapResponse>
}