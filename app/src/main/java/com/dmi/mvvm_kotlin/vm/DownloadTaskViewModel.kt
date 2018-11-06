package com.dmi.mvvm_kotlin.vm

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.dmi.mvvm_kotlin.LibApplication
import com.dmi.mvvm_kotlin.data.model.Download
import com.dmi.mvvm_kotlin.data.model.DownloadStatus
import com.dmi.mvvm_kotlin.data.usecase.DownloadUseCase
import com.dmi.mvvm_kotlin.util.ObservableManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class DownloadTaskViewModel : ViewModel() {

    @Inject
    lateinit var downloadUseCase: DownloadUseCase

    val downloadFile = MutableLiveData<Download>()
    val randomTask = MutableLiveData<String>()
    private val observableManager = ObservableManager()
    private var taskId = 0

    init {
        LibApplication.appComponent.inject(this)
    }

    @SuppressLint("CheckResult")
    fun startDownloadFile(url: String) {
        observableManager.singleTask(
                downloadUseCase.getDownloadFile(url)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { downloadFile.value = DownloadStatus("Downloading ...") }
                        .subscribe({
                            downloadFile.value = it
                        }, {
                            downloadFile.value = DownloadStatus(it.message!!)
                        })
        , 500)
    }

    fun startRandomTask(timer: Long, user: String) {
        randomTask.value = "start fetching user data ..."
        observableManager.singleTask(Observable.timer(timer, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { taskId++ }
                .subscribe {
                    randomTask.value = "receive user data: $user, time: $timer, task id: $taskId"
                }, 200)
    }

    override fun onCleared() {
        super.onCleared()

        observableManager.destroy()
    }
}