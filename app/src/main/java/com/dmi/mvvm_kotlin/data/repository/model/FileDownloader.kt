package com.dmi.mvvm_kotlin.data.repository.model

import android.annotation.SuppressLint
import android.os.Environment
import android.webkit.MimeTypeMap
import com.dmi.mvvm_kotlin.data.model.Download
import com.dmi.mvvm_kotlin.data.model.DownloadStatus
import com.dmi.mvvm_kotlin.data.model.DownloadSuccess
import com.dmi.mvvm_kotlin.data.repository.DownloadRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.Okio
import java.io.File

open class FileDownloader : DownloadRepository {

    @SuppressLint("CheckResult")
    override fun downloadFile(url: String): Observable<Download> {
        return Observable.fromCallable {
            downloadFile(url, null, null)
        }.subscribeOn(Schedulers.io())
    }

    private fun downloadFile(url: String, name: String?, fileExt: String?): Download {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        try {
            val response = client.newCall(request).execute()
            val contentType = response.header("content-type", null)
            var ext = MimeTypeMap.getSingleton().getExtensionFromMimeType(contentType)
            ext = if (ext == null) {
                fileExt
            } else {
                ".$ext"
            }

            // use provided name or generate a temp file
            val file = if (name != null) {
                val filename = String.format("%s%s", name, ext)
                File(dir.absolutePath, filename)
            } else {
                val timestamp = "sample"
                File.createTempFile(timestamp, ext, dir)
            }

            val body = response.body()
            val sink = Okio.buffer(Okio.sink(file))

            body?.source().use { input ->
                sink.use { output ->
                    output.writeAll(input)
                }
            }

            return DownloadSuccess("file://" + file!!.absolutePath)

        } catch (e: Exception) {
            //TODO: handle error when can't download
            print(e.message)
        }

        return DownloadStatus()
    }
}