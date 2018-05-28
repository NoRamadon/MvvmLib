package com.dmi.mvvm_kotlin.bus.event

import android.support.v4.app.Fragment
import com.google.android.gms.maps.model.LatLng

abstract class RxBusEvent

data class ReplaceFragmentEvent(val fragment: Fragment): RxBusEvent()

data class AddFragmentEvent(val fragment: Fragment): RxBusEvent()

data class ReceiveLatLngEvent(val latLng: LatLng): RxBusEvent()