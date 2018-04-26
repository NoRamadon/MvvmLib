package com.dmi.mvvm_kotlin.bus.event

import android.support.v4.app.Fragment

abstract class RxBusEvent

data class ReplaceFragmentEvent(val fragment: Fragment) : RxBusEvent()