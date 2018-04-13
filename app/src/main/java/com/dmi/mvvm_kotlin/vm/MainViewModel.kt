package com.dmi.mvvm_kotlin.vm

import android.app.Application
import com.dmi.mvvm_kotlin.view.base.BaseViewModel

class MainViewModel: BaseViewModel(Application()) {

    var sampleString: String = "Hello from view model."

}