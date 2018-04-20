package com.dmi.mvvm_kotlin.data.repository

import com.dmi.mvvm_kotlin.data.model.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class UserRepository {

    fun getUser(): Observable<User> {
        return Observable.interval(1, TimeUnit.SECONDS)
                .map { val nameRandom = Random().nextInt(UserNames.values().size)
                    User(UserNames.values()[nameRandom].name) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) }
}

internal enum class UserNames {
    FLUFFY, SCRATCHY, MINNIE, TIGER, LUCY, PUMPKIN
}