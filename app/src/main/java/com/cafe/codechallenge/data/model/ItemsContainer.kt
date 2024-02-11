package com.cafe.codechallenge.data.model

import androidx.annotation.Keep

/**
 * Created by emadmahouti on 2/8/24
 */
@Keep
data class ItemsContainer<T>(
    val results: List<T>,
    val page: Int,
    val total_pages: Int,
    val total_results: Int,
)