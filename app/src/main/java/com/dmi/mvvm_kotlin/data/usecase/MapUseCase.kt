package com.dmi.mvvm_kotlin.data.usecase

import com.dmi.mvvm_kotlin.data.remote.WebService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


open class MapUseCase {

    fun getAddress(latLng: String): Single<String> {
        val webService = builder.build().create(WebService::class.java)
        return webService.getAddress(latLng)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { it.results[0].formatted_address }
    }

    private val builder: Retrofit.Builder
        get() = Retrofit.Builder()
                .baseUrl("http://maps.googleapis.com/maps/api/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

}