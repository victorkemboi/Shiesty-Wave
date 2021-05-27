package com.mes.shiestywave.utils

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.* // ktlint-disable no-wildcard-imports
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import timber.log.Timber
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import kotlin.coroutines.cancellation.CancellationException

fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }

    this.observeForever(observer)

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}

fun CoroutineScope.safeLaunchWithIo(
    block: suspend CoroutineScope.() -> Unit
): Job =
    this.launch(Dispatchers.IO) {
        try {
            block()
        } catch (ce: CancellationException) {
            // You can ignore or log this exception
        } catch (e: Exception) {
            // Here it's better to at least log the exception
            Timber.d(e)
        }
    }

fun CoroutineScope.safeLaunchWithDefault(
    block: suspend CoroutineScope.() -> Unit
): Job =
    this.launch(Dispatchers.Default) {
        try {
            block()
        } catch (ce: CancellationException) {
            // You can ignore or log this exception
        } catch (e: Exception) {
            // Here it's better to at least log the exception
            Timber.d(e)
        }
    }

fun CoroutineScope.safeLaunchWithMain(
    block: suspend CoroutineScope.() -> Unit
): Job =
    this.launch(Dispatchers.Main) {
        try {
            block()
        } catch (ce: CancellationException) {
            // You can ignore or log this exception
        } catch (e: Exception) {
            // Here it's better to at least log the exception
            Timber.d(e)
        }
    }

fun CoroutineScope.safeLaunch(
    block: suspend CoroutineScope.() -> Unit
): Job =
    this.launch {
        try {
            block()
        } catch (ce: CancellationException) {
            // You can ignore or log this exception
            Timber.d(ce)
        } catch (e: Exception) {
            // Here it's better to at least log the exception
            Timber.d(e)
        }
    }

fun LifecycleCoroutineScope.safeLaunchWhenCreated(
    block: suspend LifecycleCoroutineScope.() -> Unit
): Job =
    this.launchWhenCreated {
        try {
            block()
        } catch (ce: CancellationException) {
            // You can ignore or log this exception
            Timber.d(ce)
        } catch (e: Exception) {
            // Here it's better to at least log the exception
            Timber.d(e)
        }
    }

fun LifecycleCoroutineScope.safeLaunchWhenResumed(
    block: suspend LifecycleCoroutineScope.() -> Unit
): Job =
    this.launchWhenResumed {
        try {
            block()
        } catch (ce: CancellationException) {
            // You can ignore or log this exception
            Timber.d(ce)
        } catch (e: Exception) {
            // Here it's better to at least log the exception
            Timber.d(e)
        }
    }

fun LifecycleCoroutineScope.safeLaunchWhenStarted(
    block: suspend LifecycleCoroutineScope.() -> Unit
): Job =
    this.launchWhenStarted {
        try {
            block()
        } catch (ce: CancellationException) {
            // You can ignore or log this exception
            Timber.d(ce)
        } catch (e: Exception) {
            // Here it's better to at least log the exception
            Timber.d(e)
        }
    }

suspend fun <T> Flow<List<T>>.flattenToList() =
    flatMapConcat { it.asFlow() }.toList()