package com.dmi.mvvm_kotlin.view.activity

import android.support.v4.app.Fragment
import com.dmi.mvvm_kotlin.R
import com.dmi.mvvm_kotlin.view.base.BaseActivity
import com.dmi.mvvm_kotlin.view.fragment.LoginFragment

class LoginActivity : BaseActivity() {

    override val firstFragment: Fragment by lazy { LoginFragment() }

    override fun getLayoutId(): Int {
       return R.layout.activity_login
    }
}
