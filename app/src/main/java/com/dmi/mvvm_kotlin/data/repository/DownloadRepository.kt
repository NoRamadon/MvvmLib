package com.dmi.mvvm_kotlin.data.repository

import com.dmi.mvvm_kotlin.data.model.Download
import io.reactivex.Observable

interface DownloadRepository {
    fun downloadFile(url: String): Observable<Download>
}