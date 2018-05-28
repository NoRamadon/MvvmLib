package com.dmi.mvvm_kotlin.data.usecase

import com.dmi.mvvm_kotlin.data.model.MapResponse
import com.dmi.mvvm_kotlin.data.model.Place
import com.dmi.mvvm_kotlin.data.remote.WebService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


open class MapUseCase {

    private val webService = builder.build().create(WebService::class.java)

    fun getAddress(latLng: String): Single<MapResponse> {
        return webService.getAddress(latLng)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getNearBy(latlng: String): Single<Place> {
        return webService.getNearBy(latlng)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private val builder: Retrofit.Builder
        get() = Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

}