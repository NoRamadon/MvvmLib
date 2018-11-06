package com.dmi.mvvm_kotlin.view.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dmi.mvvm_kotlin.R
import com.dmi.mvvm_kotlin.data.model.DownloadStatus
import com.dmi.mvvm_kotlin.data.model.DownloadSuccess
import com.dmi.mvvm_kotlin.vm.DownloadFragmentViewModel
import com.dmi.mvvm_kotlin.vm.DownloadTaskViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_download.*


class DownloadFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_download, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val downloadTaskViewModel = ViewModelProviders.of(activity!!)
                .get(DownloadFragmentViewModel::class.java)

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
                    Picasso.with(activity)
                            .load(it.path)
                            .into(downloadImageView)
                }
            }
        })

        downloadButton.setOnClickListener {
            downloadTaskViewModel.startDownloadFile("https://i.imgur.com/4LF6cGW.png")
        }
    }
}
