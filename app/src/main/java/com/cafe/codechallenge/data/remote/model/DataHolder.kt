package com.cafe.codechallenge.data.remote.model

/**
 * Created by emadmahouti on 2/8/24
 */
sealed class DataHolder<out T>(val message: String?) {
    data class Success<T>(val body: T) : DataHolder<T>(null)
    data class Failure<T>(val code: Int, val msg: String?) : DataHolder<T>(msg)
}

inline fun <T> DataHolder<T>.doOnSuccess(block: (value: T) -> Unit): DataHolder<T> {
    if(this is DataHolder.Success<T>) block(body)
    return this
}

inline fun <T> DataHolder<T>.doOnError(block: (code: Int, msg: String?) -> Unit): DataHolder<T> {
    if(this is DataHolder.Failure<T>) block(code, msg)
    return this
}

/**
 * the determination of what the 'Message' truly represents lies within the discretion of the NetworkRunner.
 * example: It could be came from meta in response
 */
inline fun <T> DataHolder<T>.ifHasMessage(block: (message: String) -> Unit): DataHolder<T> {
    if(this.message != null) block(message)
    return this
}