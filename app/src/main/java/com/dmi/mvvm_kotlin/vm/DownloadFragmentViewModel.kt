package com.dmi.mvvm_kotlin.vm

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.dmi.mvvm_kotlin.LibApplication
import com.dmi.mvvm_kotlin.data.model.Download
import com.dmi.mvvm_kotlin.data.model.DownloadStatus
import com.dmi.mvvm_kotlin.data.usecase.DownloadUseCase
import com.dmi.mvvm_kotlin.util.ObservableManager
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject


class DownloadFragmentViewModel : ViewModel() {

    @Inject
    lateinit var downloadUseCase: DownloadUseCase

    val downloadFile = MutableLiveData<Download>()
    private val observableManager = ObservableManager()

    init {
        LibApplication.appComponent.inject(this)
    }

    @SuppressLint("CheckResult")
    fun startDownloadFile(url: String) {
        observableManager.add(
                downloadUseCase.getDownloadFile(url)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { downloadFile.value = DownloadStatus("Downloading ...") }
                        .subscribe({
                            downloadFile.value = it
                        }, {
                            downloadFile.value = DownloadStatus(it.message!!)
                        })
        )
    }

    override fun onCleared() {
        super.onCleared()

        observableManager.destroy()
    }
}