package com.dmi.mvvm_kotlin.data.model

sealed class Download

data class DownloadSuccess(val path: String = ""): Download()
data class DownloadStatus(val status: String = ""): Download()