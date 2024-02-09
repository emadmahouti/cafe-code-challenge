package com.cafe.codechallenge.presentation.ui.movieList

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import com.cafe.codechallenge.data.remote.model.ItemsContainer
import com.cafe.codechallenge.data.remote.model.MovieResponse
import com.cafe.codechallenge.presentation.common.base.ConcatWithLoadingAdapter
import com.cafe.codechallenge.presentation.common.generateStaticViewId
import com.cafe.codechallenge.presentation.ui.movieList.items.MovieListAdapter
import com.pixy.codebase.common.CRecyclerView
import com.pixy.codebase.common.viewgroup.VLinearLayout
import com.pixy.codebase.common.viewgroup.items.UIStateInterface
import com.pixy.codebase.providers.ParamsProvider
import com.pixy.codebase.utils.OneArgCallback
import com.pixy.codebase.utils.dpToPx

/**
 * Created by emadmahouti on 2/8/24
 */
class MovieListView(private val context: Context): VLinearLayout(context), UIStateInterface {

    private val recyclerView = CRecyclerView(context)
    private val listAdapter = MovieListAdapter()

    private val concatAdapter = ConcatWithLoadingAdapter(listAdapter)
    var paginationCallback: OneArgCallback<Int> by listAdapter::paginationCallback

    init {
        val sideMargin = 15.dpToPx
        val cellCount = 3

        addView(recyclerView, ParamsProvider.Linear.availableHeightParams())

        with(recyclerView) {
            id = generateStaticViewId()
            setAsGrid(cellCount).spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position + 1 == adapter?.itemCount)
                        cellCount
                    else
                        1
                }
            }
            setVerticalItemSpace(sideMargin)
            setHorizontalItemSpace(sideMargin)
            adapter = concatAdapter.concat()
        }
    }

    fun set(items: ItemsContainer<MovieResponse>) {
        listAdapter.addItems(items.results)
        listAdapter.setPaginationData(items.total_pages, items.page)
    }

    override fun showContent() {
        concatAdapter.showContent()
    }

    override fun showRetry() {
        concatAdapter.showRetry()
    }

    override fun showLoading() {
        concatAdapter.showLoading()
    }

    override fun noData() {
        concatAdapter.showContent()
    }
}