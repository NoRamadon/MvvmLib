package com.dmi.mvvm_kotlin.data.usecase

import com.dmi.mvvm_kotlin.data.model.Download
import com.dmi.mvvm_kotlin.data.repository.DownloadRepository
import io.reactivex.Observable

class DownloadInteractor(private val repository: DownloadRepository): DownloadUseCase {
    override fun getDownloadFile(url: String): Observable<Download> {
        return repository.downloadFile(url)
    }
}