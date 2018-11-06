package com.dmi.mvvm_kotlin.view.fragment


import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dmi.mvvm_kotlin.R
import com.dmi.mvvm_kotlin.util.ObservableManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_live_data.*
import java.util.*
import java.util.concurrent.TimeUnit

class LiveDataFragment : Fragment() {

    private val testLiveData = MutableLiveData<String>()
    private val subscriptionManager = ObservableManager()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live_data, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        emitString()

        testLiveData.observe(activity!!, Observer {
            liveDataText?.text = it
        })
    }

    @SuppressLint("CheckResult")
    private fun emitString(){
        subscriptionManager.singleTask(
                Observable.interval(1, 10, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe ({
                            testLiveData.postValue(Calendar.getInstance().time.toString())
                        }, {
                            testLiveData.postValue(it.message)
                        }), 100)
    }

    override fun onDetach() {
        super.onDetach()

        subscriptionManager.destroy()
    }

}
