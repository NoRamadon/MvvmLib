package com.dmi.mvvm_kotlin.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.dmi.mvvm_kotlin.data.usecase.MapUseCase
import com.dmi.mvvm_kotlin.view.base.BaseViewModel
import com.google.android.gms.maps.model.LatLng


class MainViewModel(application: Application) : BaseViewModel(application) {

    val pinAddress = MutableLiveData<String>()

    fun receiveUpdateLocation(latLng: LatLng) {
        MapUseCase().getAddress(latLng).subscribe ({
            pinAddress.postValue(it.results[0].formatted_address)
        },{
            Log.e("error", "${it.message}")
        })
    }


}