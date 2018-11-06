package com.dmi.mvvm_kotlin.data.repository

import android.arch.lifecycle.Observer
import com.dmi.mvvm_kotlin.data.model.Download
import com.dmi.mvvm_kotlin.data.model.DownloadSuccess
import com.dmi.mvvm_kotlin.vm.DownloadTaskViewModel
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins
import org.mockito.Mockito
import org.mockito.Mockito.*


class DownloadRepositoryTest {

    @Before
    fun initialize(){
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
    }

    @Test
    fun `when download file success should called on error`(){

    }

    @Test
    fun `when download file failed should called on success`(){

    }
}