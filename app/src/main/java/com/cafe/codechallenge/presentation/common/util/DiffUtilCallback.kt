package com.cafe.codechallenge.presentation.common.util

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by emadmahouti on 2/8/24
 */
class DiffUtilCallback<T>(
    private val oldItems: List<T>,
    private val newItems: List<T>,
    callback: DiffUtilCallbackHelper<T>.() -> Unit
) : DiffUtil.Callback() {

    private val callbackHelper = DiffUtilCallbackHelper<T>().apply(callback)

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return callbackHelper.areItemsTheSame?.invoke(oldItems[oldItemPosition], newItems[newItemPosition]) ?: false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return callbackHelper.areContentsTheSame?.invoke(oldItems[oldItemPosition], newItems[newItemPosition]) ?: false
    }
}