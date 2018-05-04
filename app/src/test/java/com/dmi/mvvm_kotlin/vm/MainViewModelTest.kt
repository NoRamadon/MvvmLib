package com.dmi.mvvm_kotlin.vm

import android.arch.lifecycle.Observer
import com.dmi.mvvm_kotlin.data.model.Address
import com.dmi.mvvm_kotlin.data.model.AddressResponse
import com.dmi.mvvm_kotlin.data.model.MapResponse
import com.dmi.mvvm_kotlin.data.usecase.MapUseCase
import com.dmi.mvvm_kotlin.mock
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.*
import java.util.concurrent.TimeUnit


class MainViewModelTest{

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mapUseCase: MapUseCase

    @Before
    fun setup() {

        mainViewModel = Mockito.mock(MainViewModel::class.java)
        mapUseCase = Mockito.mock(MapUseCase::class.java)

        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            _ -> Schedulers.trampoline() }
    }

    @After
    fun teatDown(){
        RxAndroidPlugins.reset()
    }

    @Test
    fun getAddressFromLatlng(){

        val mapResponse = MapResponse(listOf(Address("sample")))

        //sample latlng
        val result = mapUseCase.getAddress("11.574529,104.926686")

        val testObserver = TestObserver<MapResponse>()
        result.subscribe(testObserver)
        testObserver
                .awaitTerminalEvent(10, TimeUnit.SECONDS)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        Mockito.verify(mainViewModel, atLeastOnce()).onAddressReceive(mapResponse)
    }
}