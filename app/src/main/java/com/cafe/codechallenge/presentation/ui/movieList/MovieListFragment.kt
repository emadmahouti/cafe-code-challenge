package com.cafe.codechallenge.presentation.ui.movieList

import android.view.View
import com.cafe.codechallenge.presentation.common.base.BaseFragmentVM
import com.pixy.codebase.extensions.views
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by emadmahouti on 2/8/24
 */
class MovieListFragment: BaseFragmentVM<MovieViewModel>() {

    override val viewModel: MovieViewModel by viewModel()

    private val movieView: MovieListView by views {
        MovieListView(requireContext())
    }
    override fun observe() {
        with(viewModel) {
            _movieLiveData.observe(viewLifecycleOwner) {items ->
                movieView.set(items)
            }

            stateLiveData.observe(viewLifecycleOwner) {

            }
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