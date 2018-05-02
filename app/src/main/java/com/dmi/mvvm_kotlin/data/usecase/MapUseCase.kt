package com.dmi.mvvm_kotlin.data.usecase

import com.dmi.mvvm_kotlin.data.model.GoogleMapResponse
import com.dmi.mvvm_kotlin.data.remote.WebService
import com.google.android.gms.maps.model.LatLng
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MapUseCase {

    private val baseUrl = "http://maps.googleapis.com/maps/api/"

    fun getAddress(latLng: LatLng): Observable<GoogleMapResponse> {
        val webService = builder.build().create(WebService::class.java)
        return webService.getAddress("${latLng.latitude},${latLng.longitude}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private val builder: Retrofit.Builder
        get() = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

}