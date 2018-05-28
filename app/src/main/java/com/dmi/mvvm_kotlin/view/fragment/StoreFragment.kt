package com.dmi.mvvm_kotlin.view.fragment


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.android.databinding.library.baseAdapters.BR
import com.dmi.mvvm_kotlin.R
import com.dmi.mvvm_kotlin.databinding.FragmentStoreBinding
import com.dmi.mvvm_kotlin.view.adapter.PlaceAdapter
import com.dmi.mvvm_kotlin.view.base.BaseFragment
import com.dmi.mvvm_kotlin.vm.StoreFragmentViewModel
import kotlinx.android.synthetic.main.fragment_store.*

class StoreFragment : BaseFragment<FragmentStoreBinding, StoreFragmentViewModel>() {

    override val viewModel by lazy { StoreFragmentViewModel(activity!!.application) }

    override val bindingVariable by lazy { BR.vm }

    override val layoutId by lazy { R.layout.fragment_store }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PlaceAdapter()
        placeList.layoutManager = LinearLayoutManager(context)
        placeList.adapter = adapter

        viewModel.allPlace.observe(this, Observer(adapter::submitList))
    }
}
