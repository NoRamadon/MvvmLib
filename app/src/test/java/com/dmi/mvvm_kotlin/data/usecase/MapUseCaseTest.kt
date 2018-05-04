package com.dmi.mvvm_kotlin.data.usecase

import com.dmi.mvvm_kotlin.data.model.Address
import com.dmi.mvvm_kotlin.data.model.MapResponse
import com.dmi.mvvm_kotlin.data.remote.WebService
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import java.util.concurrent.TimeUnit


class MapUseCaseTest {

    private lateinit var mapUseCase: MapUseCase
    private lateinit var webService: WebService

    @Before
    fun setup() {
        mapUseCase = Mockito.mock(MapUseCase::class.java)
        webService = Mockito.mock(WebService::class.java)

        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            _ -> Schedulers.trampoline() }
    }

    @Test
    fun successGetResponseAddress() {
        //give address for latlng
        val response = MapResponse(listOf(
                Address("Seng Thuon (St. 104), Phnom Penh, Cambodia")
        ))

        //sample latlng
        val result = mapUseCase.getAddress("11.574529,104.926686")

        val testObserver = TestObserver<MapResponse>()
        result.subscribe(testObserver)
        testObserver
                .awaitTerminalEvent(10, TimeUnit.SECONDS)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()
        assertThat(listResult[0].results[0].formatted_address, `is`(response.results[0].formatted_address))
    }

    @Test
    fun error400GetResponseAddress() {
        val error400 = "HTTP 400 Bad Request"

        val result = mapUseCase.getAddress(anyString())
        val testObserver = TestObserver<MapResponse>()
        result.subscribe(testObserver)
        testObserver
                .awaitDone(10, TimeUnit.SECONDS)
        testObserver.assertErrorMessage(error400)

    }

    @After
    fun teatDown(){
        RxAndroidPlugins.reset()
    }
}