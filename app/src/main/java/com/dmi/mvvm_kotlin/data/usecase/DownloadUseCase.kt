package com.dmi.mvvm_kotlin.data.usecase

import com.dmi.mvvm_kotlin.data.model.Download
import io.reactivex.Observable

interface DownloadUseCase {
    fun getDownloadFile(url: String): Observable<Download>
}