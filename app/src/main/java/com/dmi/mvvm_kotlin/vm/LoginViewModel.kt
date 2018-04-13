package com.dmi.mvvm_kotlin.vm

import android.app.Application
import android.text.Editable
import android.util.Log
import com.dmi.mvvm_kotlin.view.base.BaseViewModel


class LoginViewModel : BaseViewModel(Application()) {

    fun onLoginClick() {

    }

    fun userNameTextChange(editable: Editable){
        Log.d("login", "$editable")
    }
}