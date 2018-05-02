package com.dmi.mvvm_kotlin.data.model

data class GoogleMapResponse(val results: List<Address>)

data class Address(val formatted_address: String)
