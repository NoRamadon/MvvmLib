package com.dmi.mvvm_kotlin.data.repository

import com.dmi.mvvm_kotlin.data.model.User
import java.util.*
import java.util.concurrent.TimeUnit

typealias NewUserReceived = (User) -> Unit

class UserRepository {
    private val timer = Timer()
    private val random = Random()
    private val period = TimeUnit.SECONDS.toMillis(1)

    internal fun receiveNewUser(newUserReceived: NewUserReceived) {
        timer.schedule(object : TimerTask() {
            override fun run() {
                val nameRandom = random.nextInt(UserNames.values().size)
                val ageRandom = random.nextInt(5)

                newUserReceived.invoke(User(UserNames.values()[nameRandom].name, ageRandom))
            }
        }, period, period)
    }
}

internal enum class UserNames {
    FLUFFY, SCRATCHY, MINNIE, TIGER, LUCY, PUMPKIN
}