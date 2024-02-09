package com.cafe.codechallenge.presentation.ui.movieList

import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cafe.codechallenge.R
import com.cafe.codechallenge.presentation.common.base.BaseFragmentVM
import com.pixy.codebase.common.viewgroup.items.PageState
import com.pixy.codebase.extensions.views
import com.pixy.codebase.utils.ToastManager
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by emadmahouti on 2/8/24
 */
class MovieListFragment: BaseFragmentVM<MovieViewModel>() {

    override val viewModel: MovieViewModel by viewModel(ownerProducer = {
        findNavController().getBackStackEntry(R.id.main_graph)
    })

    private val movieView: MovieListView by views {
        MovieListView(requireContext())
    }

    private var cursor: Int? = null
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
                movieView.showRetry(state.msg) {
                    cursor?.let {nonNullCursor ->
                        viewModel.getMovies(nonNullCursor)
                    }
                }
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

    private fun setupSharedTransitionAnimation() {
        val transition = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        ).also {
            it.duration = 800
        }

        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
    }

    override fun viewReady() {
        super.viewReady()
        setupSharedTransitionAnimation()

        with(movieView) {
            paginationCallback = { currentPage ->
                viewModel.getMovies(currentPage)
                cursor = currentPage
            }

            itemClickListener = {_, item, _ ->
                ToastManager(item.title).show(requireContext())
            }
        }
    }

    override fun generateView(): View = movieView
}