package com.cafe.codechallenge.data.model

/**
 * Created by emadmahouti on 2/8/24
 */
data class ItemsContainer<T>(
    val results: List<T>,
    val page: Int,
    val total_pages: Int,
    val total_results: Int,
)