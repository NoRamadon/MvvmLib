package com.dmi.mvvm_kotlin.data.model

sealed class AddressResponse{
    abstract val address: String
}

data class MapResponse(val results: List<Address>)

data class Address(val formatted_address: String)

data class DefaultResponse(override val address: String = ""): AddressResponse()

data class ReceiveResponse(override val address: String): AddressResponse()

data class ErrorResponse(val errorMessages: String, override val address: String): AddressResponse()
