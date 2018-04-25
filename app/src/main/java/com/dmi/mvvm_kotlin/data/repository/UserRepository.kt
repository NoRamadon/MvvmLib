package com.dmi.mvvm_kotlin.data.repository

import com.dmi.mvvm_kotlin.data.model.LoadUser
import com.dmi.mvvm_kotlin.data.model.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class UserRepository {

    fun getUser(): Observable<LoadUser> {
        return Observable.interval(1, TimeUnit.SECONDS)
                .map { val nameRandom = Random().nextInt(UserNames.values().size)
                    LoadUser(UserNames.values()[nameRandom].name) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) }
}

internal enum class UserNames {
    FLUFFY, SCRATCHY, MINNIE, TIGER, LUCY, PUMPKIN
}