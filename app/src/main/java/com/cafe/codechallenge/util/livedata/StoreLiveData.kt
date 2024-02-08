package com.cafe.codechallenge.util.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.cafe.codechallenge.data.remote.model.ItemsContainer

/**
 * Created by emadmahouti on 2/8/24
 * this class store all values that posted
 */
open class StoreListLiveData<T> : MutableLiveData<ItemsContainer<T>>() {
    private val listDataHolder = arrayListOf<T>()
    private var lastResults: ItemsContainer<T>? = null
        set(value) {
            field = value
            listDataHolder.addAll(value?.results ?: emptyList())
        }

    override fun observe(owner: LifecycleOwner, observer: Observer<in ItemsContainer<T>>) {
        super.observe(owner) {
            observer.onChanged(ItemsContainer(listDataHolder, lastResults?.page ?: 0, lastResults?.total_pages ?: 0, lastResults?.total_results ?: 0))
        }
    }

    override fun postValue(value: ItemsContainer<T>) {
        super.postValue(value)
        lastResults = value
    }

    fun removeData() {
        if(listDataHolder.isNotEmpty()) {
            lastResults = null
            listDataHolder.clear()
        }
    }
}