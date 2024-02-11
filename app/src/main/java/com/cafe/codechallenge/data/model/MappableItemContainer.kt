package com.cafe.codechallenge.data.model

/**
 * Created by emadmahouti on 2/11/24
 */
data class MappableItemContainer<T, I: MappableModel<T>>(
    val results: List<I>,
    val page: Int,
    val total_pages: Int,
    val total_results: Int,
): MappableModel<ItemsContainer<T>> {
    override fun map(): ItemsContainer<T> {
        return ItemsContainer(results.map { it.map() }, page, total_pages, total_results)
    }
}