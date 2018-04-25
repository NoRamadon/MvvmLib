package com.dmi.mvvm_kotlin.data.model

sealed class User {
    abstract val name: String
}

data class DefaultUser(override val name: String = "Default User"): User()
data class LoadUser(override val name: String): User()