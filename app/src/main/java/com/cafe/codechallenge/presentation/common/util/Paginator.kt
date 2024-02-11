package com.cafe.codechallenge.presentation.common.util

/**
 * Created by emadmahouti on 2/10/24
 */
interface Paginator<in Item> {

    fun load()
    fun nextKey(item: Item)
    fun invalidate()
}