package com.dmi.mvvm_kotlin.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import com.android.databinding.library.baseAdapters.BR
import com.dmi.mvvm_kotlin.R
import com.dmi.mvvm_kotlin.bus.RxBus
import com.dmi.mvvm_kotlin.bus.event.ReceiveLatLngEvent
import com.dmi.mvvm_kotlin.databinding.FragmentMainBinding
import com.dmi.mvvm_kotlin.view.base.BaseFragment
import com.dmi.mvvm_kotlin.vm.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng


class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(), OnMapReadyCallback, GoogleMap.OnCameraIdleListener {

    override val viewModel by lazy {  MainViewModel(activity!!.application)}
    override val bindingVariable by lazy { BR.vm }
    override val layoutId by lazy { R.layout.fragment_main }

    private val currentLocation = LatLng(11.574529, 104.926686)
    private val zoomLevel = 21f
    private var googleMap: GoogleMap? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val mapFragment : SupportMapFragment? =
                childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap ?: return
        this.googleMap = googleMap

        googleMap.setOnCameraIdleListener(this)
        googleMap.isMyLocationEnabled = true
        // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
        val cameraPosition = CameraPosition.Builder()
                .target(currentLocation)      // Sets the center of the map to Mountain View
                .zoom(zoomLevel)                   // Sets the zoom
                .build()                   // Creates a CameraPosition from the builder
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    override fun onCameraIdle() {
        val position = googleMap?.cameraPosition
        viewModel.receiveUpdateLocation(position!!.target)
    }
}
