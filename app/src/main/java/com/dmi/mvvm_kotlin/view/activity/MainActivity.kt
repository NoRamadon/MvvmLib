package com.dmi.mvvm_kotlin.view.activity

import android.support.v4.app.Fragment
import com.dmi.mvvm_kotlin.R
import com.dmi.mvvm_kotlin.view.base.BaseActivity
import com.dmi.mvvm_kotlin.view.fragment.MainFragment

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getFirstFragment(): Fragment {
        return MainFragment()
    }
}
