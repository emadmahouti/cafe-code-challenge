package com.cafe.codechallenge.di

import com.cafe.codechallenge.presentation.ui.movieList.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by emadmahouti on 5/13/23
 */

val viewModelModule = module {
    viewModel { MovieViewModel(get(), get()) }
}