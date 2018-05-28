package com.dmi.mvvm_kotlin.vm

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.dmi.mvvm_kotlin.LibApplication
import com.dmi.mvvm_kotlin.data.local.PlaceDao
import com.dmi.mvvm_kotlin.data.model.Place
import com.dmi.mvvm_kotlin.data.model.Result
import com.dmi.mvvm_kotlin.data.usecase.MapUseCase
import com.dmi.mvvm_kotlin.view.base.BaseViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class StoreFragmentViewModel (application: Application) : BaseViewModel(application) {

    @Inject
    lateinit var placeDao: PlaceDao

    var allPlace: LiveData<PagedList<Result>>

    init {
        getNearBy()
        LibApplication.appComponent.inject(this)

        allPlace = LivePagedListBuilder(placeDao.getAllPlace(), PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .build()).build()
    }





    private fun getNearBy(){
        MapUseCase().getNearBy("11.574471, 104.926823")
                .delay(10, TimeUnit.SECONDS)
                .subscribe (this::receiveNearBy,{
                    println(it.localizedMessage)
                })
    }

    private fun receiveNearBy(place: Place){
        //placeDao.deletePreviousPlace()
        place.results.forEach {
            placeDao.insertPlace(it)
        }
    }
}