package com.cafe.codechallenge.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.MutableLiveData
import com.cafe.codechallenge.data.local.model.MovieLocalEntity
import com.cafe.codechallenge.domain.model.MovieEntity
import com.cafe.codechallenge.util.providers.ConfigProvider
import com.pixy.codebase.common.viewgroup.items.PageState
import kotlinx.coroutines.flow.Flow


/**
 * Created by emadmahouti on 2/8/24
 */
suspend fun<T> Flow<T>.catchStateIn(liveData: MutableLiveData<T>): PageState {
    this.collect {
        liveData.postValue(it)
    }

    return PageState.NoData
}
fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    val netC = connectivityManager.getNetworkCapabilities(network)
    if(netC?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true || netC?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true)
        return true
    return false
}

fun<T> MutableLiveData<T>.default(init: T): MutableLiveData<T> {
    if(this.value == null)
        this.value = init

    return this
}

fun<T> MutableLiveData<List<T>>.invalidate(): MutableLiveData<List<T>> {
    this.value = null
    return this
}

infix fun<T> Collection<T>.diff(other: Collection<T>): Pair<Set<T>, Set<T>> {
    val left = this subtract other.toSet()
    val right = other subtract this.toSet()

    return left to right
}

fun MovieEntity.toLocalEntity(): MovieLocalEntity {
    return MovieLocalEntity(
        id,
        title,
        posterPath,
        overview,
        popularity,
        releaseDate
    )
}

val MovieEntity.image get() =
    "${ConfigProvider().image_base_url}$posterPath"
