package com.cafe.codechallenge.presentation.ui.movieList.items

import com.cafe.codechallenge.data.remote.model.ItemsContainer
import com.cafe.codechallenge.data.remote.model.MovieResponse
import com.cafe.codechallenge.presentation.common.util.Paginator

/**
 * Created by emadmahouti on 2/10/24
 */

fun interface MoviePaginatorInterface: (Int) -> Unit

class MoviePaginator(
    private val initialKey: Int,
    private val function: MoviePaginatorInterface

): Paginator<ItemsContainer<*>> {
    private var cursor = initialKey
    private var hasNext: Boolean = true

    override fun load() {
        if(hasNext)
            function.invoke(cursor)
    }

    override fun nextKey(item: ItemsContainer<*>) {
        hasNext = cursor < item.total_pages

        if(hasNext)
            cursor = item.page + 1
    }

    override fun reset() {
        cursor = initialKey
    }
}