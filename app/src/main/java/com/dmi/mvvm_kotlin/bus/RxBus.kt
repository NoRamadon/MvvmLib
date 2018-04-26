package com.dmi.mvvm_kotlin.bus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object RxBus {

    private var publisher = PublishSubject.create<Any>()

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    // Listen should return an Observable and not the publisher
    // Using ofType we filter only events that match that class type
    fun <T> listen(eventType: Class<T>): Observable<T> {
      return publisher.ofType(eventType)
    }
}