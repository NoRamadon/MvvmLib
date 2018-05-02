package com.dmi.mvvm_kotlin.data.usecase


import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class MapUseCaseTest {

    private lateinit var mapUseCase: MapUseCase


    @Before
    fun setup() {
        mapUseCase = Mockito.mock(MapUseCase::class.java)
    }

    @Test
    fun successGetResponseAddress() {


    }

    @Test
    fun failedGetResponseAddress() {

    }

    @After
    fun tearDown() {

    }

}