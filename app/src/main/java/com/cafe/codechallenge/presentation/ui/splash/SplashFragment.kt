package com.cafe.codechallenge.presentation.ui.splash

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.cafe.codechallenge.R
import com.cafe.codechallenge.presentation.common.base.BaseFragmentVM
import com.cafe.codechallenge.presentation.ui.movieList.MovieViewModel
import com.cafe.codechallenge.util.bazaarSmallLogo
import com.pixy.codebase.common.viewgroup.items.PageState
import com.pixy.codebase.extensions.views
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by emadmahouti on 2/9/24
 */
class SplashFragment: BaseFragmentVM<MovieViewModel>() {

    /**
     * share viewModel in navGraph scope with koin
     */
    override val viewModel: MovieViewModel by viewModel(ownerProducer = {
        findNavController().getBackStackEntry(R.id.main_graph)
    })

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
        if(state is PageState.Fetching) {
            splashView.showContent()
            if(!state.fetch)
                navigateToMovieList()
        } else if(state is PageState.Failure) {
            splashView.showRetry(null) {
                viewModel.paging.load()
            }
        }
    }

    private fun navigateToMovieList() {
        val extra = FragmentNavigatorExtras(splashView.getLogoView to bazaarSmallLogo)
        navigateTo(SplashFragmentDirections.gotoMovieList(), extra)
    }

    override fun generateView(): View = splashView
}