package com.cafe.codechallenge.presentation.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cafe.codechallenge.data.model.*
import com.cafe.codechallenge.presentation.common.util.Paginator
import com.cafe.codechallenge.util.default
import com.cafe.codechallenge.util.livedata.SingleLiveData
import com.pixy.codebase.common.viewgroup.items.PageState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

/**
 * Created by emadmahouti on 2/8/24
 */
open class BaseViewModel: ViewModel() {
    val toastLiveData = SingleLiveData<String>()

    inline fun <T> postValues(
        crossinline  block: suspend () -> DataHolder<ItemsContainer<T>>,
        liveData: MutableLiveData<List<T>>,
        paging: Paginator<ItemsContainer<*>>? = null
    ) =  handleRequest(block) { value ->

        paging?.nextKey(value)

        if (value.results.isEmpty())
            emit(PageState.NoData)

        liveData.default(arrayListOf()).also { liveData ->
            liveData.value?.toMutableList()?.let {items ->
                items.addAll(value.results)
                liveData.postValue(items)
            }
        }
    }

    inline fun <T> postValue(
        crossinline block: suspend () -> DataHolder<T>,
        liveData: MutableLiveData<T>
    ): Flow<PageState> {
        return handleRequest(block) {value ->
            liveData.postValue(value)
        }
    }

    inline fun<T> handleRequest(
        crossinline data: suspend () -> DataHolder<T>,
        crossinline block: suspend FlowCollector<PageState>.(value: T) -> Unit) = flow {

        emit(PageState.Fetching(true))
        val result = data.invoke()
        emit(PageState.Fetching(false))

        result.doOnSuccess { value ->
            block.invoke(this, value)
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

    fun postToast(message: String) {
        toastLiveData.postValue(message)
    }
}