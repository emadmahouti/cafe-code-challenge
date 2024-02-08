package com.cafe.codechallenge.presentation.common.util

/**
 * Created by emadmahouti on 2/8/24
 */
class DiffUtilCallbackHelper<T> {

    var areItemsTheSame: ((oldItem: T, newItem: T) -> Boolean)? = null
    private set

    var areContentsTheSame: ((oldItem: T, newItem: T) -> Boolean)? = null
        private set


    fun itemsTheSame(block: (oldItem: T, newItem: T) -> Boolean) {
        areItemsTheSame = {oldItem, newItem ->
            block.invoke(oldItem, newItem)
        }
    }

    fun contentsTheSame(block: (oldItem: T, newItem: T) -> Boolean) {
        areContentsTheSame = {oldItem, newItem ->
            block.invoke(oldItem, newItem)
        }
    }
}