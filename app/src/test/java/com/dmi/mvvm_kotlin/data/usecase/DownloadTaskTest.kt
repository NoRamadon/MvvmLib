package com.dmi.mvvm_kotlin.data.usecase

import com.dmi.mvvm_kotlin.data.model.DownloadSuccess
import com.dmi.mvvm_kotlin.data.repository.DownloadRepository
import io.reactivex.Observable
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString

class DownloadTaskTest {

    private val downloadRepository = Mockito.mock(DownloadRepository::class.java)
    private val downloadUseCase by lazy { DownloadInteractor(downloadRepository) }

    @Test
    fun `when download file failed should called onError`(){
        val response = Throwable("Error response")
        `when`(downloadRepository.downloadFile(anyString())).thenReturn(Observable.error(response))

        downloadUseCase.getDownloadFile(anyString())
                .test()
                .assertError(response)
    }

    @Test
    fun `when download done should called onNext`(){
        val response = DownloadSuccess()
        `when`(downloadRepository.downloadFile(anyString())).thenReturn(Observable.just(response))

        downloadUseCase.getDownloadFile(anyString())
                .test()
                .assertValue(response)
    }
}