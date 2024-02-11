package com.cafe.codechallenge.di

import com.cafe.codechallenge.presentation.ui.movieList.MovieViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by emadmahouti on 5/13/23
 */

val viewModelModule = module {

    factory<CoroutineDispatcher> { Dispatchers.Main }
    viewModel { params ->  MovieViewModel(get(), get(), get(), params.get()) }
}