package com.dmi.mvvm_kotlin.bus.event

import android.support.v4.app.Fragment

sealed class RxBusEvent

data class ReplaceFragmentEvent(val fragment: Fragment) : RxBusEvent()