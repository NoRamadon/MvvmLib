package com.dmi.mvvm_kotlin.data.usecase


import com.dmi.mvvm_kotlin.RxImmediateSchedulerRule
import com.dmi.mvvm_kotlin.data.model.Address
import com.dmi.mvvm_kotlin.data.model.GoogleMapResponse
import com.dmi.mvvm_kotlin.data.remote.WebService
import io.reactivex.observers.TestObserver
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class MapUseCaseTest {

    private lateinit var mapUseCase: MapUseCase
    private lateinit var webService: WebService

    @Before
    fun setup() {
        mapUseCase = Mockito.mock(MapUseCase::class.java)
        webService = Mockito.mock(WebService::class.java)
    }

    @Test
    fun successGetResponseAddress() {
        //give address for latlng
        val response = GoogleMapResponse(listOf(
                Address("Seng Thuon (St. 104), Phnom Penh, Cambodia")
        ))

        //sample latlng
        val result = mapUseCase.getAddress("11.574529,104.926686")

        val testObserver = TestObserver<String>()
        result.subscribe(testObserver)
        testObserver
                .awaitTerminalEvent(10, TimeUnit.SECONDS)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()
        assertThat(listResult[0], `is`(response.results[0].formatted_address))
    }

    @Test
    fun error400GetResponseAddress() {
        val error400 = "HTTP 400 Bad Request"

        val result = mapUseCase.getAddress(anyString())
        val testObserver = TestObserver<String>()
        result.subscribe(testObserver)
        testObserver
                .awaitDone(10, TimeUnit.SECONDS)
        testObserver.assertErrorMessage(error400)

    }
}