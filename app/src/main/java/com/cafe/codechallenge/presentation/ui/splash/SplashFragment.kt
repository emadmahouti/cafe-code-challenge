package com.cafe.codechallenge.presentation.ui.splash

import android.view.View
import com.cafe.codechallenge.R
import com.cafe.codechallenge.presentation.common.base.BaseFragmentVM
import com.cafe.codechallenge.presentation.ui.movieList.MovieViewModel
import com.pixy.codebase.common.viewgroup.items.PageState
import com.pixy.codebase.extensions.views
import org.koin.androidx.navigation.koinNavGraphViewModel

/**
 * Created by emadmahouti on 2/9/24
 */
class SplashFragment: BaseFragmentVM<MovieViewModel>() {
    override val viewModel: MovieViewModel by koinNavGraphViewModel(R.id.main_graph)

    private val splashView: SplashView by views {
        SplashView(requireContext())
    }

    override fun observe() {
         with(viewModel) {
             stateLiveData.observe(viewLifecycleOwner) { pageState ->
                 handleState(pageState)
             }
         }
    }

    private fun handleState(state: PageState) {
        if(state is PageState.Fetching && state.fetch) {
            splashView.showContent()
            navigateTo(SplashFragmentDirections.gotoMovieList())
        }
        if(state is PageState.Failure) {
            splashView.showRetry(null)
        }
    }

    override fun generateView(): View = splashView
}