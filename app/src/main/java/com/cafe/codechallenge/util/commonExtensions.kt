package com.cafe.codechallenge.util

import androidx.lifecycle.MutableLiveData
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