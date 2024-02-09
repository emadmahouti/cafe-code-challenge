package com.cafe.codechallenge.presentation.ui.movieList

import android.view.View
import com.cafe.codechallenge.R
import com.cafe.codechallenge.presentation.common.base.BaseFragmentVM
import com.pixy.codebase.common.viewgroup.items.PageState
import com.pixy.codebase.extensions.views
import org.koin.androidx.navigation.koinNavGraphViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by emadmahouti on 2/8/24
 */
class MovieListFragment: BaseFragmentVM<MovieViewModel>() {

    override val viewModel: MovieViewModel by koinNavGraphViewModel(R.id.main_graph)

    private val movieView: MovieListView by views {
        MovieListView(requireContext())
    }
    override fun observe() {
        with(viewModel) {
            movieLiveData.observe(viewLifecycleOwner) { items ->
                movieView.set(items)
            }

            stateLiveData.observe(viewLifecycleOwner) { pageState ->
                handleState(pageState)
            }
        }
    }

    private fun handleState(state: PageState) {
        when(state) {
            is PageState.Failure -> {
                movieView.showRetry(state.msg)
            }
            is PageState.Fetching -> {
                if(state.fetch) {
                    movieView.showLoading()
                } else {
                    movieView.showContent()
                }
            }
            PageState.NoData -> {}
        }
    }

    override fun viewReady() {
        super.viewReady()

        with(movieView) {
            paginationCallback = { currentPage ->
                viewModel.getMovies(currentPage)
            }
        }
    }

    override fun generateView(): View = movieView
}