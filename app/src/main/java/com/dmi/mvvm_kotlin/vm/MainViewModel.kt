package com.dmi.mvvm_kotlin.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData

import com.dmi.mvvm_kotlin.bus.RxBus
import com.dmi.mvvm_kotlin.bus.event.AddFragmentEvent
import com.dmi.mvvm_kotlin.data.model.*
import com.dmi.mvvm_kotlin.data.usecase.MapUseCase
import com.dmi.mvvm_kotlin.view.base.BaseViewModel
import com.dmi.mvvm_kotlin.view.fragment.StoreFragment
import com.google.android.gms.maps.model.LatLng


open class MainViewModel(application: Application) : BaseViewModel(application) {

    val pinAddress = MutableLiveData<AddressResponse>()

    init {
        pinAddress.value = DefaultResponse()
    }

    fun receiveUpdateLocation(latLng: LatLng) {
        val location = "${latLng.latitude},${latLng.longitude}"
        MapUseCase().getAddress(location)
                .subscribe (this::onAddressReceive,
                        this::onErrorReceive)
    }

    fun onAddressReceive(result: MapResponse){
        pinAddress.value = ReceiveResponse(validateAddress(result))
    }

    fun goAround(){
        RxBus.publish(AddFragmentEvent(StoreFragment()))
    }

    private fun onErrorReceive(error: Throwable){
        pinAddress.value = ErrorResponse(error.message!!, "...")
    }

    private fun validateAddress(result: MapResponse): String{
        return if (result.results.isNotEmpty()){
            result.results[0].formatted_address
        } else {
            "..."
        }
    }
}