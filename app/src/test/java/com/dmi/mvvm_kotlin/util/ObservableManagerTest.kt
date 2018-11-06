package com.dmi.mvvm_kotlin.util

import io.reactivex.Observable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import java.util.concurrent.TimeUnit

class ObservableManagerTest {

    private val observableManager = ObservableManager()

    @Before
    fun setUp() = RxJavaPlugins.reset()

    @After
    fun tearDown() = RxJavaPlugins.reset()

    @Test
    fun `add one disposable`(){
        observableManager.singleTask(Observable.just(anyString()).subscribe(), 1)

        assertEquals(observableManager.listDisposable.size, 1)
    }

    @Test
    fun `add normal task disposable`(){
        observableManager.add(Observable.just(anyString()).subscribe())
        observableManager.add(Observable.just(anyString()).subscribe())
        observableManager.add(Observable.just(anyString()).subscribe())

        assertEquals(observableManager.subscriptions.size(), 3)
    }

    @Test
    fun `add single task duplicate disposable`(){
        observableManager.singleTask(Observable.just(anyString()).subscribe(), 1)
        observableManager.singleTask(Observable.just(anyString()).subscribe(), 1)
        observableManager.singleTask(Observable.just(anyString()).subscribe(), 1)

        assertEquals(observableManager.subscriptions.size(), 3)
        assertEquals(observableManager.listDisposable.size, 1)
    }

    @Test
    fun `check duplicate disposable must emit only last one`(){

        val testScheduler = TestScheduler()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }

        val testObserverFirst = Observable.just("first")
                .delay(2, TimeUnit.SECONDS, testScheduler)
                .test()

        observableManager.singleTask(testObserverFirst, 1)

        val testObserverLast = Observable.just("last").test()
        observableManager.singleTask(testObserverLast, 1)

        // check first disposable should not completed
        testObserverFirst.assertNotComplete()

        // check first disposable should disposed
        assertEquals(testObserverFirst.isDisposed, true)

        // check last disposable should completed
        testObserverLast.assertComplete()
                .assertNoErrors()
                .assertResult("last")
    }

    @Test
    fun `destroy all observable`(){
        observableManager.singleTask(Observable.just(anyString()).subscribe(), 1)
        observableManager.singleTask(Observable.just(anyString()).subscribe(), 2)
        observableManager.singleTask(Observable.just(anyString()).subscribe(), 3)

        observableManager.destroy()

        assertEquals(observableManager.subscriptions.size(), 0)
        assertEquals(observableManager.listDisposable.size, 0)
    }
}