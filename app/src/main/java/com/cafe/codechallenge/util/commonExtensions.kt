package com.cafe.codechallenge.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.MutableLiveData
import com.cafe.codechallenge.data.remote.model.MovieResponse
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

fun Context.getConnectivityManager(): ConnectivityManager {
    return getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}
fun ConnectivityManager.isNetworkAvailable(): Boolean {
    val network = activeNetwork
    val netC = getNetworkCapabilities(network)
    if(netC?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true || netC?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true)
        return true
    return false
}

fun<T> MutableLiveData<T>.default(init: T): MutableLiveData<T> {
    if(this.value == null)
        this.value = init

    return this
}

fun<T> MutableLiveData<List<T>>.reset(): MutableLiveData<List<T>> {
    this.value = null
    return this
}

val MovieResponse.image get() =
    "${ConfigProvider().image_base_url}$poster_path"
