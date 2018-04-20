package com.dmi.mvvm_kotlin.vm

import android.app.Application
import android.content.Intent
import android.text.Editable
import android.util.Log
import com.dmi.mvvm_kotlin.view.activity.MainActivity
import com.dmi.mvvm_kotlin.view.base.BaseViewModel

class LoginViewModel(application: Application): BaseViewModel(application) {

    override fun onCreate() {

    }

    fun onLoginClick() {
        getApplication<Application>().startActivity(Intent(getApplication(), MainActivity::class.java))
    }

    fun userNameTextChange(editable: Editable){
        Log.d("login", "$editable")
    }
}