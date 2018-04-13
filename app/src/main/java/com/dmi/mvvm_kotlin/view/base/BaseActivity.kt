package com.dmi.mvvm_kotlin.view.base

import android.app.Fragment
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.dmi.mvvm_kotlin.R

abstract class BaseActivity: AppCompatActivity(){

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * @return fragment
     */
    abstract fun getFirstFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        fragmentManager.beginTransaction()
                .replace(R.id.mainContent, getFirstFragment())
                .addToBackStack(null)
                .commit()
    }
}