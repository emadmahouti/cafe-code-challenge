package com.cafe.codechallenge.presentation.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cafe.codechallenge.data.remote.model.*
import com.cafe.codechallenge.util.livedata.SingleLiveData
import com.pixy.codebase.common.viewgroup.items.PageState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by emadmahouti on 2/8/24
 */
open class BaseViewModel: ViewModel() {
    val toastLiveData = SingleLiveData<String>()

    inline fun <T> postValue(
        crossinline block: suspend () -> DataHolder<T>,
        liveData: MutableLiveData<T>
    ): Flow<PageState> {
        return flow {
            emit(PageState.Fetching(true))
            val result = block.invoke()
            emit(PageState.Fetching(false))

            result.doOnSuccess { value ->
                if (value is ItemsContainer<*>) {
                    if (value.results.isEmpty())
                        emit(PageState.NoData)
                }

                liveData.postValue(value)
            }

            result.doOnError { _, msg ->
                if (msg != null) {
                    emit(PageState.Failure(msg))
                }
            }

            result.ifHasMessage {value ->
                postToast(value)
            }
        }
    }

    fun <T> postValue(
        deferred: Deferred<DataHolder<T>>,
        liveData: MutableLiveData<T>
    ): Flow<PageState> {
        return postValue({deferred.await()}, liveData)
    }

    fun postToast(message: String) {
        toastLiveData.postValue(message)
    }
}