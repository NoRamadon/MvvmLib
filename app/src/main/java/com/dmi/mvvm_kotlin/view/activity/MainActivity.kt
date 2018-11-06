package com.dmi.mvvm_kotlin.view.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.dmi.mvvm_kotlin.R
import com.dmi.mvvm_kotlin.view.base.BaseActivity
import com.dmi.mvvm_kotlin.view.fragment.LiveDataFragment

class MainActivity : BaseActivity() {

    private val firstFragment: Fragment by lazy { LiveDataFragment() }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
                .replace(R.id.mainContent, firstFragment)
                .commit()
    }
}
