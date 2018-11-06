package com.dmi.mvvm_kotlin.view.activity

import android.arch.lifecycle.*
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.dmi.mvvm_kotlin.LibApplication
import com.dmi.mvvm_kotlin.R
import com.dmi.mvvm_kotlin.data.model.DownloadStatus
import com.dmi.mvvm_kotlin.data.model.DownloadSuccess
import com.dmi.mvvm_kotlin.view.base.BaseActivity
import com.dmi.mvvm_kotlin.vm.DownloadTaskViewModel
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_download.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DownloadActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_download

    private lateinit var downloadTaskViewModel: DownloadTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //register view model
        downloadTaskViewModel = ViewModelProviders.of(this)
                .get(DownloadTaskViewModel::class.java)

        //observe livedata
        downloadTaskViewModel.downloadFile.observe(this, Observer {
            when (it) {
                is DownloadStatus -> {
                    downloadStatus.visibility = View.VISIBLE
                    downloadImageView.visibility = View.GONE
                    downloadStatus.text = it.status
                }
                is DownloadSuccess -> {
                    downloadImageView.visibility = View.VISIBLE
                    downloadStatus.text = getString(R.string.done)
                    Picasso.with(this)
                            .load(it.path)
                            .into(downloadImageView)
                }
            }
        })

        downloadTaskViewModel.randomTask.observe(this, Observer {
            downloadStatus.text = it
        })

        openMvpButton.setOnClickListener {
            startMvpActivity()
        }

        downloadButton.setOnClickListener {
            downloadTaskViewModel.startDownloadFile("https://upload.wikimedia.org/wikipedia/commons/2/2d/Snake_River_%285mb%29.jpg")
        }

        randomTaskButton.setOnClickListener {
            downloadTaskViewModel.startRandomTask(3,  "user1")
            downloadTaskViewModel.startRandomTask(10,  "user2")
            downloadTaskViewModel.startRandomTask(20, "user3")
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        print("destroy")
    }

    private fun startMvpActivity() {
        startActivity(Intent(this, MvpWithViewModelActivity::class.java))
    }
}
