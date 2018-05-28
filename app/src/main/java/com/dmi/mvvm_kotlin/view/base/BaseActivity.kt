package com.dmi.mvvm_kotlin.view.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.dmi.mvvm_kotlin.R

abstract class BaseActivity : AppCompatActivity() {

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * @return fragment
     */
    abstract val firstFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContent, firstFragment)
                    .commit()
        }
    }

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 0){
            fragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}